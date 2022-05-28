package data.local

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import data.entities.Article

/**
 * Each Migration has a start and end versions and Room runs these migrations to bring the
 * database to the latest version. The migration object that can modify the database and
 * to the necessary changes.
 */
object DbMigration {
    const val DB_VERSION = 2

    val MIGRATION_ARTICLE: Array<Migration>
        get() = arrayOf(
            migrationArticle()
        )

    private fun migrationArticle(): Migration = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE ${Article.TABLE_ARTICLE} ADD COLUMN body TEXT")
        }
    }
}

