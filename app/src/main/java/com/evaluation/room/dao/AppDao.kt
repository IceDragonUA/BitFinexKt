package com.evaluation.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.evaluation.model.TickerItem
import com.evaluation.model.TickerList
import io.reactivex.Single

@Dao
interface AppDao {

    @Query("SELECT * FROM bitfinex ORDER BY mLastModified DESC LIMIT 292")
    fun getTickerList(): Single<List<TickerItem>>

    @Query("SELECT * FROM bitfinex WHERE mSymbol =:mSymbol ORDER BY mLastModified DESC")
    fun getTickerArchivedList(mSymbol: String?): Single<List<TickerItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTickerList(tickerList: List<TickerItem>)

}