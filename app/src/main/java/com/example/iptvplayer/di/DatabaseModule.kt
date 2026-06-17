package com.example.iptvplayer.di

import android.content.Context
import androidx.room.Room
import com.example.iptvplayer.data.db.IPTVDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideIPTVDatabase(
        @ApplicationContext context: Context
    ): IPTVDatabase {
        return Room.databaseBuilder(
            context,
            IPTVDatabase::class.java,
            IPTVDatabase.DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideChannelDao(database: IPTVDatabase) = database.channelDao()
}
