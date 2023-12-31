package com.example.login_firebase_tutorial.model

data class User(
    val id: String?,
    val userId: String,
    val displayName: String,
    val avatarUrl: String,
    val quote: String,
    val profession: String
){
    fun toMap(): MutableMap<String, Any>{
        return mutableMapOf(
            "userId" to this.userId,
            "displayName" to this.displayName,
            "avatarUrl" to this.avatarUrl,
            "quote" to this.quote,
            "profession" to this.profession
        )
    }
}
