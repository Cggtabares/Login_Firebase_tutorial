package com.example.login_firebase_tutorial.screen.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login_firebase_tutorial.model.User
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

class LoginScreenViewModel : ViewModel() {
    private val auth: FirebaseAuth = Firebase.auth
    private val _loading = MutableLiveData(false)

    fun signInWithEmailAndPassword(email: String, password: String, home: () -> Unit) =
        viewModelScope.launch {
            try {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnSuccessListener { authResult ->
                        Log.d(
                            "FB", "" +
                                    "singInWithEmailAndPassword Logueado!!!: ${authResult.toString()}"
                        )
                        home()
                    }
                    .addOnFailureListener { ex ->
                        // código cuando falla
                        // Tienes acceso a la excepción
                        Log.d(
                            "FB", "" +
                                    "singInWithEmailAndPassword Falló!!!: ${ex.localizedMessage}"
                        )
                        //errorLogueo()
                    }
            } catch (ex: Exception) {
                Log.d("LoginApp", "signInWithEmailAndPassword: ${ex.message}")

            }
        }

    fun createUserWithemailAndPassword(
        email: String,
        password: String,
        home: () -> Unit
    ) {
        if (_loading.value == false) {
            _loading.value = true
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val displayName =
                            task.result.user?.email?.split("@")?.get(0)
                        createUser(displayName)
                            home()
                        }
                    else{
                        Log.d("LoginApp", "createUserWithemailAndPassword: ${task.result.toString()}")
                    }
                    _loading.value = false
                }
        }

    }

    private fun createUser(displayName: String?) {
        val userId = auth.currentUser?.uid
        //val user= mutableMapOf<String, Any>()

        //user["user_id"] = userId.toString()
        //user["display_name"] = displayName.toString()

        //Usando DataClass
        val user = User(
            userId = userId.toString(),
            displayName = displayName.toString(),
            avatarUrl = "",
            quote = "Lo dificil Ya paso",
            profession = "Desarrollador",
            id = null

        ).toMap()


        FirebaseFirestore.getInstance().collection("users")
            .add(user)
            .addOnSuccessListener { {
                Log.d("AppLogin", "Creadp ${it.id}")
            } }.addOnFailureListener{
                Log.d("AppLogin", "Error al crear usuario ${it}")
            }

    }

}

