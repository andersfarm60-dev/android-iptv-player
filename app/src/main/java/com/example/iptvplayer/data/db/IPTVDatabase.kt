package com.example.iptvplayer.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.iptvplayer.data.model.ChannelEntity

@Database(
    entities = [ChannelEntity::class],
    version = 1,
    exportSchema = false
)
abstract class IPTVDatabase : RoomDatabase() {
    abstract fun channelDao(): ChannelDao

    companion object {
        const val DATABASE_NAME = "iptv_player_db"
    }
}
