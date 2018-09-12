package ru.taptm.marvelcomicssample.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.taptm.marvelcomicssample.interactor.ComicsInteractor
import ru.taptm.marvelcomicssample.interactor.FavouritesInteractor
import ru.taptm.marvelcomicssample.reposetory.IAppDataStorage
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideContext(): Context = context

    @Provides
    fun comicsInteractor(repository: IAppDataStorage): ComicsInteractor {
        return ComicsInteractor(repository)
    }

    @Provides
    fun favouritesInteractor(repository: IAppDataStorage): FavouritesInteractor {
        return FavouritesInteractor(repository)
    }
}