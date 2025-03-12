package com.cba.sample.feature.main

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.cba.sample.designsystems.components.CenterAppTopBar
import com.cba.sample.designsystems.components.navigation.BottomNavigation
import com.cba.sample.designsystems.components.navigation.NavigationGraph
import com.cba.sample.designsystems.components.navigation.getRouteTitle
import com.cba.sample.designsystems.components.navigation.showBackButton
import com.cba.sample.designsystems.components.navigation.showTitle
import com.cba.sample.designsystems.theme.SampleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // We handle all the insets manually
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            enableEdgeToEdge(
                statusBarStyle = getSystemBarStyle(),
                navigationBarStyle = getSystemBarStyle()
            )
            SampleTheme {
                MainScreenView()
            }
        }
    }
}

@Composable
private fun getSystemBarStyle(): SystemBarStyle = SystemBarStyle.run {
    val color = Color.Transparent.toArgb()
    if (isSystemInDarkTheme()) {
        dark(color)
    } else {
        light(color, color)
    }
}

@Composable
fun MainScreenView() {
    val context = LocalContext.current
    val navController = rememberNavController()// Hoist these states outside the composable function
    val actionBarDetails = rememberActionBarDetails(navController, context)

    Scaffold(
        topBar = {
            CenterAppTopBar(
                title = actionBarDetails.title,
                showTitle = actionBarDetails.showTitle,
                onActionButtonClick = {
                    // Navigate to my account screen
                    navController.navigate("account")
                },
                onBackButtonClick = if (actionBarDetails.showBackButton) {
                    { navController.navigateUp() }
                } else null
            )
        },
        bottomBar = {
            if (!actionBarDetails.showBackButton) {
                BottomNavigation(navController = navController)
            }
        },
        content = { innerPadding ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .imePadding()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(paddingValues = innerPadding)
            ) {
                NavigationGraph(navController = navController)
            }
        }
    )
}

// Helper class to hold action bar details
class ActionBarDetails(
    val title: String,
    val showBackButton: Boolean,
    val showTitle: Boolean,
)

// Function to calculate action bar details, remember the result
@Composable
fun rememberActionBarDetails(navController: NavHostController, context: Context): ActionBarDetails {
    var actionBarTitle by rememberSaveable { mutableStateOf("") }
    var showBackButton by rememberSaveable { mutableStateOf(false) }
    var showTitle by rememberSaveable { mutableStateOf(true) }

    LaunchedEffect(navController) {
        navController.currentBackStackEntryFlow.collect { backStackEntry ->
            actionBarTitle = backStackEntry.getRouteTitle(context)
            showBackButton = backStackEntry.showBackButton()
            showTitle = backStackEntry.showTitle()
        }
    }

    return remember(actionBarTitle, showBackButton, showTitle) {
        ActionBarDetails(actionBarTitle, showBackButton, showTitle)
    }
}

@Preview
@Composable
internal fun PreviewMainScreen() {
    SampleTheme {
        MainScreenView()
    }
}