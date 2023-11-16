/**
 * repository -> intermedio entre base de datos y vista
 */
package com.example.loginaplication.data.repository

import com.example.loginaplication.data.model.User

/**
 * Singleton
 * Esta clase es accesible en todo el proyecto. No se puede crear objetos de esta clase
 * constructor privado. Y tiene un objeto que contiene el listado de usuarios
 */
class UserRepository private constructor(){

    companion object{
        val dataSet: MutableList<User> = InitDataSetUser()

        private fun InitDataSetUser(): MutableList<User>{
            var dataSet: MutableList<User> = ArrayList()
            dataSet.add(User("Lourdes","Rodriguez","lourdes@iesportada.org"))
            dataSet.add(User("aaa","bbb","abc@iesportada.org"))
            return dataSet
        }
    }
}
