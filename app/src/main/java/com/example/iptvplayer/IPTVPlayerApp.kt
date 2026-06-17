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
                onChannelSelected = { channelId ->
                    navController.navigate("player/$channelId")
                }
            )
        }
        composable(
            "player/{channelId}",
            arguments = listOf(
                navArgument("channelId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val channelId = backStackEntry.arguments?.getString("channelId") ?: ""
            PlayerScreen(
                channelId = channelId,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
