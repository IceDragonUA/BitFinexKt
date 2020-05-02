package com.evaluation.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.evaluation.model.TickerItem
import com.evaluation.room.dao.AppDao
import com.evaluation.room.migrations.DATABASE_VERSION

@Database(
    entities = [
        TickerItem::class
    ], version = DATABASE_VERSION
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun appDao(): AppDao

}






