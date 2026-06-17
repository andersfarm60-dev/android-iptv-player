package com.example.iptvplayer.data.api

import retrofit2.http.GET

interface ChannelApi {
    @GET("https://iptv-org.github.io/iptv/index.m3u")
    suspend fun getChannelPlaylist(): String
}
