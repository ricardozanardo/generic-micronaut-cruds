package genericmicronautcruds.models

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class Column(val dbName: String = "", val length: Int = 256)