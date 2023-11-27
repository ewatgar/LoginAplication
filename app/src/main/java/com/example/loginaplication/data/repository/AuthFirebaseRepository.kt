package com.example.loginaplication.data.repository

import android.accounts.Account
import com.google.firebase.auth.FirebaseAuth

class AuthFirebaseRepository {
    private var authFirebase = FirebaseAuth.getInstance()

    fun login(email: String, password: String): Result<Account>{

    }
}