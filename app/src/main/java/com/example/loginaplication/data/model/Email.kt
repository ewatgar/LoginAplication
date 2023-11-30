package com.example.loginaplication.data.model

import com.example.loginaplication.data.account.AccountException
import java.util.regex.Pattern

/**
 * Esta clase comprueba que el email cumple con el patrón establecido en el método compile.
 * En caso contrario lanza una excepción.
 */
data class Email(val value:String){
    private val pattern = Pattern.compile("/\\$+@\\$+\\.\\$+")
    init{
        //check si la cadena cumple con todoo el patrón
        if (!pattern.matcher(value).matches()){
            throw AccountException.InvalidEmailFormat()
        }
    }
}
