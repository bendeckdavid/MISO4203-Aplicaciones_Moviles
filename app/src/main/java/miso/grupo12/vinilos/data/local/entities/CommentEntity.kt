package miso.grupo12.vinilos.data.local.entities

import androidx.room.Entity
import androidx.room.*

@Entity(
    tableName = "comments",
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
data class CommentEntity(
    @PrimaryKey val id: Int,
    val description: String,
    val rating: Int,
    val albumId: Int
)