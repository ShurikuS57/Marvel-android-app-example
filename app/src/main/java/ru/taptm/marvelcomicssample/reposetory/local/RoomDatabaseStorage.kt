package ru.taptm.marvelcomicssample.reposetory.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [(FavouritesData::class)], version = 1)
abstract class RoomDatabaseStorage : RoomDatabase() {

    abstract fun favouritesDataDao(): IFavouritesDataDao

}