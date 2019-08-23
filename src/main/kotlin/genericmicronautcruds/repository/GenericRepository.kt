package genericmicronautcruds.repository

interface GenericRepository<T> {
    fun list(params: Map<String, Any>?): List<T>
    fun get(params: Map<String, Any>?): T?
    fun insert(obj: T): Long
    fun delete(id: Long)
    fun update(id: Long, params: Map<String, Any>)
}