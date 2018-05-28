package ru.taptm.marvelcomicssample.reposetory.local

import android.arch.persistence.room.*
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface IFavouritesDataDao {

    @Query("SELECT * from favourites_table")
    fun getAllFavourites(): Single<List<FavouritesData>>

    @Query("SELECT * from favourites_table WHERE comicsId = :id")
    fun getFavourites(id: Long): Flowable<List<FavouritesData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(favouritesData: FavouritesData)

    @Delete()
    fun delete(favouritesData: FavouritesData)
}
