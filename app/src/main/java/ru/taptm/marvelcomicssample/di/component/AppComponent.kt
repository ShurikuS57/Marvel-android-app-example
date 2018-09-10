package ru.taptm.marvelcomicssample.di.component

import dagger.Component
import ru.taptm.marvelcomicssample.comics.comicList.presenter.ComicsPresenter
import ru.taptm.marvelcomicssample.comics.comicsdetail.presenter.ComicsDetailsPresenter
import ru.taptm.marvelcomicssample.comics.favourites.presenter.FavouritesPresenter
import ru.taptm.marvelcomicssample.di.module.AppModule
import ru.taptm.marvelcomicssample.di.module.NetworkModule
import ru.taptm.marvelcomicssample.di.module.StorageModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, NetworkModule::class, StorageModule::class))
interface AppComponent {
    fun inject(presenter: ComicsPresenter)

    fun inject(presenter: ComicsDetailsPresenter)

    fun inject(presenter: FavouritesPresenter)
}