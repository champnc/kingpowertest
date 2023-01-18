@file:OptIn(
    ExperimentalAnimationApi::class
)

package com.champnc.kingpowertest.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.champnc.kingpowertest.presentation.component.DetailScreenView
import com.champnc.kingpowertest.presentation.component.MainScreenView
import com.champnc.kingpowertest.presentation.ui.theme.KingpowertestTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KingpowertestTheme {
                val navController = rememberAnimatedNavController()

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    KingpowerTestGraph()
                }
            }
        }
    }
}

@Composable
fun KingpowerTestGraph() {
    val navController = rememberAnimatedNavController()

    AnimatedNavHost(
        navController = navController,
        startDestination = Screen.MainScreen.route
    ) {
        composable(route = Screen.MainScreen.route,
            enterTransition = {
                slideInHorizontally(tween(500))
            },
            exitTransition = {
                slideOutHorizontally(tween(500))
            }
        ) {
            MainScreenView(navController)
        }

        composable(
            route = Screen.DetailScreen.route,
            arguments = listOf(
                navArgument(DETAIL_ARGUMENT) {
                    type = NavType.StringType
                    defaultValue = ""
                }
            ),
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { it },
                    animationSpec = tween(500)
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { it },
                    animationSpec = tween(500)
                )
            }
        ) { navBackStackEntry ->
            val arg = navBackStackEntry.arguments?.getString(DETAIL_ARGUMENT)
            if (arg.isNullOrBlank()) {
                val context = LocalContext.current
                Toast.makeText(context, "No Argument to open", Toast.LENGTH_LONG)
                    .show()
            } else {
                DetailScreenView(navController, arg)
            }
        }
    }
}