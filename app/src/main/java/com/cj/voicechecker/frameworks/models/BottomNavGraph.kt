package com.cj.voicechecker.frameworks.models

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cj.voicechecker.chart.ui.ChartView
import com.cj.voicechecker.history.ui.HistoryView
import com.cj.voicechecker.home.ui.HomeView
import com.cj.voicechecker.more.ui.MoreView

@Composable
fun BottomNavGraph(navHostController: NavHostController){
    NavHost(navController = navHostController, startDestination = BottomNavItem.home.screenRoute){
        composable(BottomNavItem.home.screenRoute){
            HomeView()
        }

        composable(BottomNavItem.chart.screenRoute){
            ChartView()
        }

        composable(BottomNavItem.history.screenRoute){
            HistoryView()
        }

        composable(BottomNavItem.more.screenRoute){
            MoreView()
        }
    }
}