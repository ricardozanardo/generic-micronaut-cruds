package genericmicronautcruds.repository

fun buildFilterString(params: Map<String, Any>?): String {
    if (params.isNullOrEmpty()) {
        return ""
    }

    val filter = params.entries.joinToString(separator = " and ") {(k, v) -> "$k = '$v'" }

    return "where $filter"
}