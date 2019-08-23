package genericmicronautcruds.models

import com.mysql.cj.jdbc.result.ResultSetMetaData
import java.io.Serializable
import java.sql.ResultSet
import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.javaField


open class Model : Serializable {

    companion object {
        val entities = mutableMapOf<String, MutableMap<String, Any>>()
    }

    fun toMap(): Map<String, Any> {
//        val cachedEntityAttributes = entities[this::class.simpleName]
//
//        if (cachedEntityAttributes != null) {
//            return cachedEntityAttributes
//        }

        val klazz = this::class

        val attributes = mutableMapOf<String, Any>()

        for (prop in klazz.memberProperties) {
            val annotation = prop.javaField!!.getAnnotation(Column::class.java)
            if (annotation != null) {
                if (annotation.dbName.isNotBlank()) {
                    attributes[annotation.dbName] = prop.getter.call(this)!!
                } else {
                    attributes[prop.name] = prop.getter.call(this)!!
                }
            }
        }

        entities[this::class.simpleName!!] = attributes

        return attributes
    }
}

fun Map<String, Any>.getUpdateSql(): String {
    return this.entries.joinToString { entry -> "${entry.key} = '${entry.value}'" }
}

fun <T: Model> ResultSet.toEntity(klazz: KClass<*>): T? {
    val constructor: KFunction<T> = klazz.constructors.first() as KFunction<T>

    val rsFields = (this.metaData as ResultSetMetaData).fields.map {
        it.columnLabel to it.mysqlType.className
    }.toMap()

    val params = constructor.parameters.map {
        getObject(it.name, Class.forName(rsFields[it.name]))
    }

    return constructor.call(*params.toTypedArray())
}