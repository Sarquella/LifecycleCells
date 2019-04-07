package dev.sarquella.sample.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dev.sarquella.sample.entities.ContactEntity
import dev.sarquella.sample.util.INITIAL_CONTACTS
import dev.sarquella.sample.util.ioThread


/*
 * Created by Adrià Sarquella on 16/08/2018.
 * adria@sarquella.dev
 */

@Database(
    entities = [ContactEntity::class],
    version = 1
)

abstract class AppDb : RoomDatabase() {

    companion object {

        private const val DB_NAME = "APP_DB"

        private var database: AppDb? = null

        @Synchronized
        fun getInstance(context: Context): AppDb {
            if (database == null) {
                database = Room.databaseBuilder(
                    context.applicationContext,
                    AppDb::class.java,
                    DB_NAME
                ).addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        fillDatabase(context.applicationContext)
                    }
                }).build()
            }

            return database!!
        }

        private fun fillDatabase(context: Context) {
            ioThread {
                getInstance(context).getContactDao().insert(INITIAL_CONTACTS)
            }
        }
    }

    abstract fun getContactDao(): ContactDao
}
 
 