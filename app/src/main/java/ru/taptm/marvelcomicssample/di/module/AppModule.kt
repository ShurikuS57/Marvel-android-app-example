package ru.taptm.marvelcomicssample.di.module

import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import ru.taptm.marvelcomicssample.reposetory.local.RoomDatabaseStorage
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideDatabase() : RoomDatabaseStorage {
        return Room.databaseBuilder(context,
                RoomDatabaseStorage::class.java, "Sample.db")
                .fallbackToDestructiveMigration()
                .build()
    }
}