package ru.taptm.marvelcomicssample.reposetory.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import ru.taptm.marvelcomicssample.base.App

@Database(entities = [(FavouritesData::class)], version = 1)
abstract class RoomDatabaseStorage : RoomDatabase() {

    abstract fun favouritesDataDao(): IFavouritesDataDao

    companion object {
        @Volatile private var INSTANCE: RoomDatabaseStorage? = null

        fun getInstance(): RoomDatabaseStorage =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: buildDatabase(App.context()).also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        RoomDatabaseStorage::class.java, "Sample.db")
                        .fallbackToDestructiveMigration()
                        .build()
    }
}