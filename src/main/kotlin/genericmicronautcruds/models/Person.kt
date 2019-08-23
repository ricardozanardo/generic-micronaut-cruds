package genericmicronautcruds.models

data class Person  (
        @Column val id: Long,
        @Column val name: String,
        @Column val gender: String,
        @Column("EMAIL") val email: String
) : Model()