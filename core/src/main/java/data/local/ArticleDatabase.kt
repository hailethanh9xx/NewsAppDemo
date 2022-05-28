package data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import data.entities.Article
import data.local.dao.ArticleDao

/**
 * Created by Aiden ( hai Le Thanh ) on 27/05/2022.
 * aiden9xx@gmail.com
 */
/**
 * ArticleDatabase provides DAO [ArticleDao] by using method [getArticleDao].
 */
@Database(
    entities = [Article::class],
    version = DbMigration.DB_VERSION
)
abstract class ArticleDatabase : RoomDatabase() {

    abstract fun getArticleDao(): ArticleDao

    companion object {
        private const val DB_NAME = "database_articles"

        @Volatile
        private var INSTANCE: ArticleDatabase? = null

        fun getInstance(context: Context): ArticleDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ArticleDatabase::class.java,
                    DB_NAME
                ).addMigrations(*DbMigration.MIGRATION_ARTICLE)
                    .allowMainThreadQueries()
                    .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}
