package genericmicronautcruds

import io.micronaut.runtime.Micronaut

object GenericApplication {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("")
                .mainClass(GenericApplication.javaClass)
                .start()
    }
}