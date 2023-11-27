package com.example.login_firebase_tutorial.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.login_firebase_tutorial.screen.TutorialSplashScreen
import com.example.login_firebase_tutorial.screen.home.Home
import com.example.login_firebase_tutorial.screen.login.AppLoginScreen

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = AppScreens.SplashScreen.name
        ){
        composable(AppScreens.SplashScreen.name){
            TutorialSplashScreen(navController = navController)
        }
        composable(AppScreens.LoginScreen.name){
            AppLoginScreen(navController = navController)
        }
        composable(AppScreens.AppHomeScreen.name){
            Home(navController = navController, viewModel())
        }
    }
}