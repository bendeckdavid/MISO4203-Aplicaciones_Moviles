package miso.grupo12.vinilos.data.local
import androidx.room.Database
import androidx.room.RoomDatabase
import miso.grupo12.vinilos.data.local.dao.AlbumDao
import miso.grupo12.vinilos.data.local.entities.*

@Database(
    entities = [
        AlbumEntity::class,
        TrackEntity::class,
        CommentEntity::class,
        PerformerEntity::class,
        PerformerAlbumCrossRef::class
    ],
    version = 1, // Siempre comienza en 1
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun albumDao(): AlbumDao
}