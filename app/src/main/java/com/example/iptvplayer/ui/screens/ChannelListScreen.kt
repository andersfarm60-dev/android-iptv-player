package com.example.iptvplayer.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.iptvplayer.data.model.ChannelEntity
import com.example.iptvplayer.ui.viewmodel.ChannelViewModel

@Composable
fun ChannelListScreen(
    viewModel: ChannelViewModel = hiltViewModel(),
    onChannelSelected: (String, String) -> Unit
) {
    val channels = viewModel.channels.collectAsState(initial = emptyList()).value
    val isLoading = viewModel.isLoading.collectAsState().value
    val error = viewModel.error.collectAsState().value

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            error != null -> {
                Column(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Error: $error", color = Color.Red)
                    Text(
                        "Retry",
                        modifier = Modifier
                            .clickable { viewModel.clearError() }
                            .padding(16.dp),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
            else -> {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(channels) { channel ->
                        ChannelListItem(
                            channel = channel,
                            onPlay = {
                                onChannelSelected(channel.id, channel.name)
                            },
                            onFavoriteClick = {
                                viewModel.toggleFavorite(channel.id, channel.isFavorite)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ChannelListItem(
    channel: ChannelEntity,
    onPlay: () -> Unit,
    onFavoriteClick: () -> Unit
) {
    ListItem(
        headlineContent = { Text(channel.name) },
        supportingContent = { Text(channel.url) },
        modifier = Modifier.fillMaxWidth(),
        trailingContent = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                IconButton(onClick = onPlay) {
                    Icon(
                        imageVector = Icons.Filled.PlayArrow,
                        contentDescription = "Play",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                IconButton(onClick = onFavoriteClick) {
                    Icon(
                        imageVector = if (channel.isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = if (channel.isFavorite) Color.Red else Color.Gray
                    )
                }
            }
        }
    )
}
