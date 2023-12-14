package com.example.loginaplication.ui.signin.usecase

import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginaplication.data.account.Account
import com.example.loginaplication.data.network.Resource
import com.example.loginaplication.data.repository.AuthFirebaseRepository
import kotlinx.coroutines.launch


const val TAG = "ViewModel"

class SignInViewModel : ViewModel() {
    //LiveData que controlan los datos introducidos en la IU
    var email = MutableLiveData<String>()
    var password = MutableLiveData<String>()

    //state es una variable de tipo LiveData, el cual tendrá su Observer en el Fragment
    // y controla las excepciones/casos de uso de la operación Login
    private var state = MutableLiveData<SignInState>()

    //Crear la clase sellada que permitirá gestionar las excepciones de la vista


    /**
     * Esta función se ejecuta directamente desde el fichero XML al usar dataBinding
     * android:onClick="@{()->viewmodel.validateCredentials()"
     */
    fun validateCredentials(){
        /* Ejemplos de prueba
        Log.i(TAG, "El email es: ${email.value}, y el password es: ${password.value}")
        email.value =  "nuevo valor"*/

        when{
            TextUtils.isEmpty(email.value) -> state.value = SignInState.EmailEmptyError
            TextUtils.isEmpty(password.value) -> state.value = SignInState.PasswordEmptyError
            //EmailFormat
            //PasswordFormat

            else -> {
                //Se crea una corrutina que suspende el hilo princupal hasta que el
                //bloque withContext del repositorio termine de ejecutarse
                viewModelScope.launch {
                    //Vamos a ejecutar el login del repositorio -> que pregunta a la capa de la infraestructura (firebase, bbdd, etc)
                    state.value = SignInState.Loading(true)

                    //hay que checkear si el usuario(email) y el password están en la base de datos
                    //La respuesta del Repositorio es Asíncrona
                    //val result = UserRepository.login(email.value!!, password.value!!)
                    val result = AuthFirebaseRepository.login(email.value!!,password.value!!)

                    //OBLIGATORIO: pausar/quitar el FragmentDialog antes de mostras el error, ya que el Fragment
                    //está pausado
                    state.value = SignInState.Loading(false)

                    when (result){
                        //No se sabe que tipo de dato es (T), así que se va a querer obtener un Account
                        // T se pone como asterisco, acepta TOD.O tipo de dato

                        is Resource.Success<*> -> {
                            //Aquí tenemos que hacer un Casting Seguro porque el tipo de dato es genérica (*)
                            val account: Account? = result.data as? Account
                            state.value = SignInState.Success(account!!)
                        }
                        is Resource.Error -> {
                            Log.i(TAG, "Información del dato ${result.exception.message}")
                            state.value = SignInState.AuthenticationError(result.exception.message!!)
                        }
                    }
                }

            }
        }

    }

    /**
     * Se crea sólo la función de obtención de la variable State. No se puede modificar su
     * valor fuera de ViewModel
     */
    fun getState() : LiveData<SignInState>{
        return state
    }
}