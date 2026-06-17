package com.example.iptvplayer.data.repository

import com.example.iptvplayer.data.db.ChannelDao
import com.example.iptvplayer.data.model.ChannelEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChannelRepository @Inject constructor(
    private val channelDao: ChannelDao
) {
    fun getAllChannels(): Flow<List<ChannelEntity>> = channelDao.getAllChannels()

    fun getFavoriteChannels(): Flow<List<ChannelEntity>> = channelDao.getFavoriteChannels()

    suspend fun getChannelById(id: String): ChannelEntity? = channelDao.getChannelById(id)

    suspend fun insertChannels(channels: List<ChannelEntity>) = channelDao.insertChannels(channels)

    suspend fun toggleFavorite(id: String, isFavorite: Boolean) =
        channelDao.updateFavorite(id, isFavorite)

    // Sample channels - в реальном приложении загружались бы с сервера
    fun getSampleChannels(): List<ChannelEntity> {
        return listOf(
            ChannelEntity(
                id = "1",
                name = "BBC News",
                url = "http://example.com/stream1.m3u8",
                logo = null
            ),
            ChannelEntity(
                id = "2",
                name = "CNN",
                url = "http://example.com/stream2.m3u8",
                logo = null
            ),
            ChannelEntity(
                id = "3",
                name = "ESPN",
                url = "http://example.com/stream3.m3u8",
                logo = null
            ),
            ChannelEntity(
                id = "4",
                name = "National Geographic",
                url = "http://example.com/stream4.m3u8",
                logo = null
            ),
            ChannelEntity(
                id = "5",
                name = "Discovery Channel",
                url = "http://example.com/stream5.m3u8",
                logo = null
            )
        )
    }
}
