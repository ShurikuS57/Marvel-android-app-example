package ru.taptm.marvelcomicssample.reposetory.local

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "favourites_table")
class FavouritesData(@PrimaryKey var comicsId: Long,
                     @ColumnInfo(name = "date") var date: Long,
                     @ColumnInfo(name = "title") var title: String = "",
                     @ColumnInfo(name = "image_url") var imageUrl: String = "")
