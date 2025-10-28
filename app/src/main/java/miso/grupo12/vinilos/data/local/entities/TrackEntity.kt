package miso.grupo12.vinilos.data.local.entities

import androidx.room.Entity
import androidx.room.*

@Entity(
    tableName = "tracks",
    foreignKeys = [
        ForeignKey(
            entity = AlbumEntity::class,
            parentColumns = ["id"],
            childColumns = ["albumId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["albumId"])]
)
data class TrackEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val duration: String,
    val albumId: Int
)