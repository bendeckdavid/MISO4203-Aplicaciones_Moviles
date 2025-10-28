package miso.grupo12.vinilos.data.mappers

import miso.grupo12.vinilos.data.local.entities.*
import miso.grupo12.vinilos.data.remote.models.* // Tus modelos de la API

// ====================================================================
// 1. Mapeo de la Entidad Principal (Album)
// ====================================================================

/** Convierte el modelo de la API en la entidad principal de Room. */
fun Album.toAlbumEntity(): AlbumEntity =
    AlbumEntity(
        id = this.id,
        name = this.name,
        cover = this.cover,
        releaseDate = this.releaseDate,
        description = this.description,
        genre = this.genre,
        recordLabel = this.recordLabel
    )

// ====================================================================
// 2. Mapeo de Entidades 1:N (Tracks y Comments)
// ====================================================================

/** * Convierte un TrackApiModel en TrackEntity.
 * Requiere que se inyecte la clave foránea (albumId) del padre.
 */
fun Track.toTrackEntity(albumId: Int): TrackEntity =
    TrackEntity(
        id = this.id,
        name = this.name,
        duration = this.duration,
        albumId = albumId // <-- ¡Inyección de la clave foránea!
    )

/** * Convierte un CommentApiModel en CommentEntity.
 * Requiere que se inyecte la clave foránea (albumId) del padre.
 */
fun Comment.toCommentEntity(albumId: Int): CommentEntity =
    CommentEntity(
        id = this.id,
        description = this.description,
        rating = this.rating,
        albumId = albumId // <-- ¡Inyección de la clave foránea!
    )

// ====================================================================
// 3. Mapeo de Entidades N:N (Performers)
// ====================================================================

/** Convierte el modelo de Performer en la entidad de Performer. */
fun Performer.toPerformerEntity(): PerformerEntity =
    PerformerEntity(
        id = this.id,
        name = this.name,
        image = this.image,
        description = this.description,
        birthDate = this.birthDate,
        creationDate = this.creationDate
    )

/** * Genera la lista de referencias cruzadas para la relación N:N.
 * Cada performer requiere un registro que lo vincule al álbum actual.
 */
fun generateAlbumPerformerCrossRefs(albumId: Int, performers: List<Performer>): List<PerformerAlbumCrossRef> =
    performers.map { performer ->
        PerformerAlbumCrossRef(
            albumId = albumId,
            performerId = performer.id
        )
    }

// ====================================================================
// 4. Función Maestra de Aplanamiento (Usada en el Repositorio)
// ====================================================================

/**
 * Función de alto nivel que toma una respuesta completa de la API
 * y la descompone en todos los componentes necesarios para la inserción en Room.
 * @return Un objeto contenedor con todas las listas de Entidades listas para el DAO.
 */
data class AlbumComponents(
    val albumEntity: AlbumEntity,
    val trackEntities: List<TrackEntity>,
    val commentEntities: List<CommentEntity>,
    val performerEntities: List<PerformerEntity>,
    val crossRefs: List<PerformerAlbumCrossRef>
)

fun Album.toRoomComponents(): AlbumComponents {
    val currentAlbumId = this.id

    return AlbumComponents(
        albumEntity = this.toAlbumEntity(),

        // Mapea y añade la clave foránea para cada pista
        trackEntities = this.tracks.map { it.toTrackEntity(currentAlbumId) },

        // Mapea y añade la clave foránea para cada comentario
        commentEntities = this.comments.map { it.toCommentEntity(currentAlbumId) },

        // Mapea la lista de artistas
        performerEntities = this.performers.map { it.toPerformerEntity() },

        // Genera la lista de referencias cruzadas
        crossRefs = generateAlbumPerformerCrossRefs(currentAlbumId, this.performers)
    )
}