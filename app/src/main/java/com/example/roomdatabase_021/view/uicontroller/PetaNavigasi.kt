package com.example.roomdatabase_021.view.uicontroller

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.roomdatabase_021.view.EntrySiswaScreen
import com.example.roomdatabase_021.view.HomeScreen
import com.example.roomdatabase_021.view.route.DestinasiEntry
import com.example.roomdatabase_021.view.route.DestinasiHome

@Composable
fun SiswaApp(navController: NavHostController= rememberNavController(), modifier: Modifier){
    HostNavigasi(navController = navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HostNavigasi(
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = Modifier
    ){
        composable(DestinasiHome.route){
            HomeScreen(
                navigateToItemEntry =  {navController.navigate(DestinasiEntry.route)},
            )
        }
        composable(DestinasiEntry.route){
            EntrySiswaScreen(navigateBack = {navController.popBackStack()})
        }
    }
}