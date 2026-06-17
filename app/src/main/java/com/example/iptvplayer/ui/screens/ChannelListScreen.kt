package com.example.iptvplayer.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

data class Channel(
    val id: String,
    val name: String,
    val url: String,
    val logo: String?
)

@Composable
fun ChannelListScreen(
    onChannelSelected: (String) -> Unit
) {
    // Sample channels
    val channels = listOf(
        Channel("1", "Channel 1", "http://example.com/stream1.m3u8", null),
        Channel("2", "Channel 2", "http://example.com/stream2.m3u8", null),
        Channel("3", "Channel 3", "http://example.com/stream3.m3u8", null),
    )

    LazyColumn {
        items(channels) { channel ->
            ListItem(
                headlineContent = { Text(channel.name) },
                modifier = Modifier.clickable {
                    onChannelSelected(channel.id)
                }
            )
        }
    }
}
