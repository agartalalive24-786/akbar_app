package com.akbar.chessvisionpro.auth

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val prefs: SharedPreferences = context.getSharedPreferences(
        "auth_prefs",
        Context.MODE_PRIVATE
    )
    
    suspend fun signUp(email: String, password: String, username: String): User {
        val user = User(
            id = System.currentTimeMillis().toString(),
            email = email,
            username = username
        )
        
        saveUser(user, password)
        return user
    }
    
    suspend fun signIn(email: String, password: String): User {
        val savedUser = getUserByEmail(email)
            ?: throw Exception("User not found")
        
        val savedPassword = prefs.getString("password_${email}", "")
        if (savedPassword != password) {
            throw Exception("Invalid password")
        }
        
        setCurrentUser(savedUser)
        return savedUser
    }
    
    suspend fun signOut() {
        prefs.edit().remove("current_user").apply()
    }
    
    fun isUserLoggedIn(): Boolean {
        return prefs.contains("current_user")
    }
    
    fun getCurrentUser(): User? {
        val userId = prefs.getString("current_user", null) ?: return null
        val email = prefs.getString("user_email_$userId", "")
        val username = prefs.getString("user_username_$userId", "")
        val profileImageUrl = prefs.getString("user_profile_$userId", "")
        
        return User(
            id = userId,
            email = email ?: "",
            username = username ?: "",
            profileImageUrl = profileImageUrl ?: ""
        )
    }
    
    private fun getUserByEmail(email: String): User? {
        val userId = prefs.getString("user_id_$email", null) ?: return null
        return User(
            id = userId,
            email = email,
            username = prefs.getString("user_username_$userId", "") ?: ""
        )
    }
    
    private fun saveUser(user: User, password: String) {
        prefs.edit().apply {
            putString("user_id_${user.email}", user.id)
            putString("user_email_${user.id}", user.email)
            putString("user_username_${user.id}", user.username)
            putString("password_${user.email}", password)
            apply()
        }
    }
    
    private fun setCurrentUser(user: User) {
        prefs.edit().apply {
            putString("current_user", user.id)
            apply()
        }
    }
}
