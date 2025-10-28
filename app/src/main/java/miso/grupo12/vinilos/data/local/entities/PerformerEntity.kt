package miso.grupo12.vinilos.data.local.entities

import androidx.room.Entity
import androidx.room.*


@Entity(tableName = "performers")
data class PerformerEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val image: String,
    val description: String,
    val birthDate: String?, // Para músicos individuales
    val creationDate: String? // Para bandas
)