package com.example.rma_final_project.data.local

import androidx.room.*
import com.example.rma_final_project.data.model.WeatherEntity
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
abstract class WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(entity: WeatherEntity): Completable

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insertAll(entities: List<WeatherEntity>): Completable

    @Query("DELETE FROM weather")
    abstract fun deleteAll()

    @Transaction
    open fun deleteAndInsertAll(entities: List<WeatherEntity>) {
        deleteAll()
        insertAll(entities).blockingAwait()
    }


    @Query("SELECT * FROM weather WHERE city LIKE :name LIMIT :days ")
    abstract fun getAllForCity(name:String,days:Int): Observable<List<WeatherEntity>>
}