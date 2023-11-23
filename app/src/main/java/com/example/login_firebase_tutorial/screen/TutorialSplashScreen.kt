package com.example.login_firebase_tutorial.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.login_firebase_tutorial.navigation.AppScreens
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay

@Composable
fun TutorialSplashScreen(navController: NavController) {
    LaunchedEffect(key1 = true) {
        delay(1500L)
        //navController.navigate(AppScreens.LoginScreen.name)
        if(FirebaseAuth.getInstance().currentUser?.email.isNullOrEmpty()){
            navController.navigate(AppScreens.LoginScreen.name)
        }else{
            navController.navigate(AppScreens.AppHomeScreen.name){
                popUpTo(AppScreens.SplashScreen.name){
                    inclusive = true
                }
            }
        }

    }

    Surface(
        modifier = Modifier
            .padding(15.dp)
            .size(330.dp),
        color = Color.White,
        border = BorderStroke(width = 2.dp, color = Color.Gray)
    ) {
        Column(
            modifier = Modifier.padding(1.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "App Tutorial",
                style = MaterialTheme.typography.titleMedium,

                )
            Spacer(modifier = Modifier.height(15.dp))
            Text(text = "aprendiendo Crear cuenta, login, navigation y conectandose a Firebase")

        }
    }

}