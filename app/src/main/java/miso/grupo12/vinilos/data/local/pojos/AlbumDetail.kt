package miso.grupo12.vinilos.data.local.pojos
import androidx.room.Embedded
import androidx.room.Relation
import androidx.room.Junction
import miso.grupo12.vinilos.data.local.entities.*

// POJO para obtener el Álbum completo con todos sus detalles
data class AlbumDetail(
    // 1. Entidad principal (Album)
    @Embedded
    val album: AlbumEntity,

    // 2. Relación 1:N con Tracks
    @Relation(
        parentColumn = "id",
        entityColumn = "albumId"
    )
    val tracks: List<TrackEntity>,

    // 3. Relación 1:N con Comments
    @Relation(
        parentColumn = "id",
        entityColumn = "albumId"
    )
    val comments: List<CommentEntity>,

    // 4. Relación N:N con Performers (usando Junction)
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            PerformerAlbumCrossRef::class,
            parentColumn = "albumId",
            entityColumn = "performerId"
        )
    )
    val performers: List<PerformerEntity>
)