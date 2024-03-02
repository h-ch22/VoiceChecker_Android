package com.cj.voicechecker.frameworks.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.cj.voicechecker.frameworks.models.BottomNavGraph
import com.cj.voicechecker.frameworks.models.BottomNavItem
import com.cj.voicechecker.ui.theme.VoiceCheckerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView() {
    val items = listOf<BottomNavItem>(
        BottomNavItem.home,
        BottomNavItem.chart,
        BottomNavItem.history,
        BottomNavItem.more
    )

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    var currentRouteName by remember {
        mutableStateOf("홈")
    }

    var currentRouteIcon by remember{
        mutableStateOf(Icons.Rounded.Home)
    }

    VoiceCheckerTheme {
        Scaffold(
            topBar = {
                MediumTopAppBar(title = { Text(text = currentRouteName) })
            },

            bottomBar = {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surface
                ) {
                    items.forEach { item ->
                        NavigationBarItem(
                            selected = currentRoute == item.screenRoute,
                            onClick = {
                                currentRouteName = item.title
                                currentRouteIcon = item.icon

                                navController.navigate(item.screenRoute) {
                                    navController.graph.startDestinationRoute?.let {
                                        popUpTo(it) {
                                            saveState = true
                                        }

                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            }, icon = { Icon(imageVector = item.icon, contentDescription = null)},
                            label = { Text(text = item.title)},
                            alwaysShowLabel = false
                        )
                    }
                }
            }
        ) {
            Surface(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(), color = MaterialTheme.colorScheme.surface
            ) {
                Box(modifier = Modifier.padding(20.dp)){
                    BottomNavGraph(navHostController = navController)

                    FloatingActionButton(onClick = { /*TODO*/ }, modifier = Modifier.align(Alignment.BottomEnd)) {
                        Icon(imageVector = Icons.Rounded.Add, contentDescription = null)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainViewPreview() {
    MainView()
}