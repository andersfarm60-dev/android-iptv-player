package com.example.iptvplayer.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iptvplayer.data.model.ChannelEntity
import com.example.iptvplayer.data.repository.ChannelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChannelViewModel @Inject constructor(
    private val repository: ChannelRepository
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    val channels: Flow<List<ChannelEntity>> = repository.getAllChannels()
    val favoriteChannels: Flow<List<ChannelEntity>> = repository.getFavoriteChannels()

    init {
        loadChannels()
    }

    private fun loadChannels() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null

                // Загружаем sample каналы
                val sampleChannels = repository.getSampleChannels()
                repository.insertChannels(sampleChannels)

                _isLoading.value = false
            } catch (e: Exception) {
                _error.value = e.message ?: "Unknown error"
                _isLoading.value = false
            }
        }
    }

    fun toggleFavorite(channelId: String, currentValue: Boolean) {
        viewModelScope.launch {
            try {
                repository.toggleFavorite(channelId, !currentValue)
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun clearError() {
        _error.value = null
    }
}
