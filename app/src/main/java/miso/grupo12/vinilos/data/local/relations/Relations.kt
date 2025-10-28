package miso.grupo12.vinilos.data.local.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import miso.grupo12.vinilos.data.local.entities.*

// ============================================
// Album con sus tracks
// ============================================
data class AlbumWithTracks(
    @Embedded val album: AlbumEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "albumId"
    )
    val tracks: List<TrackEntity>
)

// ============================================
// Album con sus comentarios
// ============================================
data class AlbumWithComments(
    @Embedded val album: AlbumEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "albumId"
    )
    val comments: List<CommentEntity>
)

// ============================================
// Album con sus performers (Many-to-Many)
// ============================================
data class AlbumWithPerformers(
    @Embedded val album: AlbumEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = PerformerAlbumCrossRef::class,
            parentColumn = "albumId",
            entityColumn = "performerId"
        )
    )
    val performers: List<PerformerEntity>
)

// ============================================
// Album COMPLETO - Como viene del API
// ============================================
data class AlbumComplete(
    @Embedded val album: AlbumEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "albumId"
    )
    val tracks: List<TrackEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "albumId"
    )
    val comments: List<CommentEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = PerformerAlbumCrossRef::class,
            parentColumn = "albumId",
            entityColumn = "performerId"
        )
    )
    val performers: List<PerformerEntity>
)

// ============================================
// Performer con sus albums
// ============================================
data class PerformerWithAlbums(
    @Embedded val performer: PerformerEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = PerformerAlbumCrossRef::class,
            parentColumn = "performerId",
            entityColumn = "albumId"
        )
    )
    val albums: List<AlbumEntity>
)