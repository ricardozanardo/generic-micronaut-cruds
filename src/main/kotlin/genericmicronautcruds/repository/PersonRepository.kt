package genericmicronautcruds.repository

import io.micronaut.context.annotation.Requires
import genericmicronautcruds.models.Person
import genericmicronautcruds.models.getUpdateSql
import genericmicronautcruds.models.toEntity
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.simple.SimpleJdbcInsert
import javax.inject.Singleton

@Singleton
@Requires(beans = [JdbcTemplate::class])
class PersonRepository(private val jdbcTemplate: JdbcTemplate) : GenericRepository<Person> {

    private val patientInsert = SimpleJdbcInsert(jdbcTemplate).apply {
        tableName = "PATIENTS"
        setGeneratedKeyName("id")
    }

    override fun get(params: Map<String, Any>?): Person? {
        return list(params).firstOrNull()
    }

    override fun list(params: Map<String, Any>?): List<Person> {
        val patients = ArrayList<Person>()
        val filter = buildFilterString(params)

        jdbcTemplate.query("select * from PATIENTS $filter") { rs ->
            val patient = rs.toEntity<Person>(Person::class)!!
            patients.add(patient)
        }

        return patients
    }

    override fun insert(obj: Person): Long {
        val id = patientInsert.executeAndReturnKey(obj.toMap()).toLong()
        return get(mapOf("id" to id))!!.id
    }

    override fun update(id: Long, params: Map<String, Any>) {
        jdbcTemplate.execute("update PATIENTS set ${params.getUpdateSql()} where ID = $id")
    }

    override fun delete(id: Long) {
        jdbcTemplate.execute("delete from PATIENTS where ID = $id")
    }
}