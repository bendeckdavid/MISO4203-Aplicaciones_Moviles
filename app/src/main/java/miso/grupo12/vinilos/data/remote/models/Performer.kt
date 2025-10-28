package miso.grupo12.vinilos.data.remote.models

data class Performer(
    val id: Int,
    val name: String,
    val image: String,
    val description: String,
    val birthDate: String? = null,
    val creationDate: String? = null
)