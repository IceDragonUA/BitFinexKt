package com.evaluation.dagger

import android.content.Context
import androidx.room.Room
import com.evaluation.network.RestAdapter
import com.evaluation.network.RestClient
import com.evaluation.room.AppDatabase
import com.evaluation.room.dao.AppDao
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class DataModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        @Singleton
        fun appRetrofit(client: RestClient) = RestAdapter(client.httpClient)

        @JvmStatic
        @Provides
        @Singleton
        fun appDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "sc_db")
                    .addMigrations()
                    .build()
        }

        @JvmStatic
        @Provides
        @Singleton
        fun appDao(appDatabase: AppDatabase): AppDao = appDatabase.appDao()

        @JvmStatic
        @Provides
        @Singleton
        fun gson(): Gson =  GsonBuilder().create()

    }

}