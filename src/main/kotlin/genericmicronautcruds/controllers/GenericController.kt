package genericmicronautcruds.controllers

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Put
import genericmicronautcruds.models.Model
import genericmicronautcruds.services.GenericService

open class GenericController<T: Model>(private val genericService: GenericService<T>) {

    @Get("/list", produces = [MediaType.APPLICATION_JSON])
    fun list(request: HttpRequest<*>): List<T> {
        val params = getParamMap(request.parameters)
        println("Retrieving data. Filtered by: $params")
        return genericService.list(params)
    }

    @Get("/get/{id}", produces = [MediaType.APPLICATION_JSON])
    fun get(id: Long): T? {
        println("Retrieving object. Filtered by Id: $id")
        return genericService.get(mapOf("id" to id))
    }

    @Put("/", produces = [MediaType.TEXT_PLAIN])
    fun insert(obj: T): Long {
        println("Inserting object: $obj")
        return genericService.insert(obj)
    }

    @Put("/{id}", produces = [MediaType.TEXT_PLAIN])
    fun update(id: Long, request: HttpRequest<*>): HttpResponse<*> {
        val params = getParamMap(request.parameters) ?: return HttpResponse.badRequest("No params were informed to update.")
        println("Updating object with params: $params")
        genericService.update(id, params)
        return HttpResponse.ok("Object updated")
    }

    @Delete("/{id}")
    fun delete(id: Long) {
        println("Deleting object with Id: $id")
        genericService.delete(id)
    }

}