//este fichero se mete dentro de 'infraestructure' en Invoice

package com.example.loginaplication.data.repository

//en Invoice, la dependencia se escribe como:
//implementation(project(":domain:entity"))

import com.example.loginaplication.data.account.Account
import com.example.loginaplication.data.account.AccountState
import com.example.loginaplication.data.model.BusinessProfile
import com.example.loginaplication.data.model.Email
import com.example.loginaplication.data.network.Resource
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class AuthFirebaseRepository private constructor(){
    companion object{
        private var authFirebase = FirebaseAuth.getInstance()

        suspend fun login(email: String, password: String): Resource{
            lateinit var account: Account

            //TODO: crash lateinit property account has not been initialized
            withContext(Dispatchers.IO){
                try {
                    val authResult: AuthResult = authFirebase.signInWithEmailAndPassword(email,password).await()
                    //si da un error, directamente lanza una excepcion, nunca llega a esta parte del codigo
                    val user = authResult.user!!

                    account = Account.create(user.hashCode(), Email(email), password, user.displayName)
                } catch (e: Exception){
                    Resource.Error(e)
                }
            }
            return Resource.Success(data= account)
        }
    }
}