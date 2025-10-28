package miso.grupo12.vinilos.data.local.entities

import androidx.room.Entity
import androidx.room.*

@Entity(
    tableName = "performer_album_cross_ref",
    primaryKeys = ["performerId", "albumId"],
    foreignKeys = [
        ForeignKey(
            entity = PerformerEntity::class,
            parentColumns = ["id"],
            childColumns = ["performerId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = AlbumEntity::class,
            parentColumns = ["id"],
            childColumns = ["albumId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["performerId"]),
        Index(value = ["albumId"])
    ]
)
data class PerformerAlbumCrossRef(
    val performerId: Int,
    val albumId: Int
)