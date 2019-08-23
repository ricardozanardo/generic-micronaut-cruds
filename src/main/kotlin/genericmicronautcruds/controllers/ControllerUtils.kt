package genericmicronautcruds.controllers

import io.micronaut.http.HttpParameters

fun getParamMap(parameters: HttpParameters?): Map<String, Any>? {
    parameters ?: return null

    if (parameters.isEmpty) {
        return null
    }

    return parameters.map {
        it.key to it.value.first()
    }.toMap()
}