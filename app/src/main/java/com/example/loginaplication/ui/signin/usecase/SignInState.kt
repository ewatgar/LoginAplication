package com.example.loginaplication.ui.signin.usecase

import com.example.loginaplication.data.account.Account

sealed class SignInState{
    data object EmailEmptyError: SignInState()
    data object EmailFormatError: SignInState()
    data object PasswordEmptyError: SignInState()
    data object PasswordFormatError: SignInState()

    //Las imagenes se guardan como enum, nunca en viewmodel
    data class AuthenticationError(var message:String): SignInState()
    //Hay que cambiarlo de data object a data class porque hay que añadir un parametro
    //en el viewmodel se pasa data si es Success, y se pasa como Account
    data class Success(var account: Account): SignInState()

    //Se debe crear una clase que contiene un valor booleano que indica si se muestra el
    data class Loading(var value: Boolean): SignInState()
}
