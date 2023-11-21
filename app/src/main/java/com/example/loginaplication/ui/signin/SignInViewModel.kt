package com.example.loginaplication.ui.signin

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


const val TAG = "ViewModel"

class SignInViewModel : ViewModel() {
    var email = MutableLiveData<String>()
    var password = MutableLiveData<String>()


    /**
     * Esta función se ejecuta directamente desde el fichero XML al usa dataBinding
     * android:onClick="@{()->viewmodel.validateCredentials()"
     */
    fun validateCredentials(){
        Log.i(TAG, "El email es: ${email.value}, y el password es: ${password.value}")
        email.value =  "nuevo valor"
    }
}