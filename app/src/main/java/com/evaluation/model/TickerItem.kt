package com.evaluation.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Vladyslav Havrylenko
 * @since 02.05.2020
 */
@Entity(tableName = "bitfinex")
data class TickerItem(
    @PrimaryKey(autoGenerate = true)
    val mId :Int? = null,
    val mSymbol: String,
    val mBid: Float,
    val mBidSize: Float,
    val mAsk: Float,
    val mAskSize: Float,
    val mDailyChange: Float,
    val mDailyChangeRelative: Float,
    val mLastPrice: Float,
    val mVolume: Float,
    val mHigh: Float,
    val mLow: Float,
    val mLastModified: Long = System.currentTimeMillis()
)