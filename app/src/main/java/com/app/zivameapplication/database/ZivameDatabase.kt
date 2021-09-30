package com.app.zivameapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.app.zivameapplication.model.CartModel

@Database(entities = [CartModel::class],version = 1,exportSchema = false)

abstract class ZivameDatabase:RoomDatabase() {
    abstract val cartDao:CartDao

    companion object {

        private var DB_INSTANCE: ZivameDatabase? = null

        val MIGRATION: Migration = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {

            }
        }

        fun getDatabase(context: Context): ZivameDatabase {
            if (DB_INSTANCE == null) {
                synchronized(this) {
                    if (DB_INSTANCE == null) {
                        // Get PhraseRoomDatabase database instance
                        DB_INSTANCE = Room.databaseBuilder(context, ZivameDatabase::class.java, "zivame")
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            //.addMigrations(MIGRATION)
                            .build()
                    }
                }
            }
            return DB_INSTANCE!!
        }
    }
}