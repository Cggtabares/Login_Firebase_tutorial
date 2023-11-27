package com.example.login_firebase_tutorial.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.login_firebase_tutorial.navigation.AppScreens
import com.example.login_firebase_tutorial.screen.login.LoginScreenViewModel

@Composable
fun Home(navController: NavController, viewModel: LoginScreenViewModel){
    Column {
        Text(text = "Estamos en Home!!!!")
        Button(
            onClick = { viewModel.signOutAccount().also {
                navController.navigate(AppScreens.LoginScreen.name) }
                }) {
            Text(text = "Cerrar Sesion")

        }
    }
    
    
}