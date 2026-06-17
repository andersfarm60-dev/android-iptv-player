package com.example.iptvplayer

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.iptvplayer.ui.screens.ChannelListScreen
import com.example.iptvplayer.ui.screens.PlayerScreen

@Composable
fun IPTVPlayerApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "channels"
    ) {
        composable("channels") {
            ChannelListScreen(
                onChannelSelected = { channelId, channelName ->
                    navController.navigate("player/$channelId/$channelName")
                }
            )
        }
        composable(
            "player/{channelId}/{channelName}",
            arguments = listOf(
                navArgument("channelId") { type = NavType.StringType },
                navArgument("channelName") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val channelId = backStackEntry.arguments?.getString("channelId") ?: ""
            val channelName = backStackEntry.arguments?.getString("channelName") ?: ""
            val channelUrl = "http://example.com/stream.m3u8" // В реальности берётся из БД

            PlayerScreen(
                channelId = channelId,
                channelName = channelName,
                channelUrl = channelUrl,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
