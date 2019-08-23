package genericmicronautcruds.services

import genericmicronautcruds.repository.GenericRepository

open class GenericService<T>(private val repository: GenericRepository<T>) {

    fun list(params: Map<String, Any>?): List<T> {
        return repository.list(params)
    }

    fun get(params: Map<String, Any>?): T? {
        return repository.get(params)
    }

    fun insert(obj: T): Long {
        return repository.insert(obj)
    }

    fun update(id: Long, params: Map<String, Any>) {
        return repository.update(id, params)
    }

    fun delete(id: Long) {
        repository.delete(id)
    }


}

