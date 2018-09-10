package ru.taptm.marvelcomicssample.di.module

import dagger.Module
import dagger.Provides
import ru.taptm.marvelcomicssample.reposetory.AppDataStorageImpl
import ru.taptm.marvelcomicssample.reposetory.IAppDataStorage
import ru.taptm.marvelcomicssample.reposetory.local.IFavouritesDataDao
import ru.taptm.marvelcomicssample.reposetory.local.RoomDatabaseStorage
import ru.taptm.marvelcomicssample.reposetory.network.service.IApiService
import javax.inject.Singleton

@Module
class StorageModule {

    @Provides
    @Singleton
    fun provideAppDataStore(apiService: IApiService, favouritesDao: IFavouritesDataDao): IAppDataStorage {
        return AppDataStorageImpl(apiService, favouritesDao)
    }

    @Provides
    @Singleton
    fun provideFavoritesDataDao(database: RoomDatabaseStorage): IFavouritesDataDao {
        return database.favouritesDataDao()
    }
}