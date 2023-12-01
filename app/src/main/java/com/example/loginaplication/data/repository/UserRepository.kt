/**
 * repository -> intermedio entre base de datos y vista
 */
package com.example.loginaplication.data.repository

import android.content.res.Resources
import com.example.loginaplication.data.model.User
import com.example.loginaplication.data.network.Resource
import com.example.loginaplication.data.network.ResourceList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

/**
 * Singleton
 * Esta clase es accesible en todoo el proyecto. No se puede crear objetos de esta clase
 * constructor privado. Y tiene un objeto que contiene el listado de usuarios
 */
class UserRepository private constructor() {

    //TODO hacer que se inicialicen los datos de la lista

    init {

    }

    companion object {
        val dataSet: MutableList<User> = InitDataSetUser()

        private fun InitDataSetUser(): MutableList<User> {
            var dataSet: MutableList<User> = ArrayList()
            dataSet.add(User("Lourdes", "Rodriguez", "lourdes@iesportada.org"))
            dataSet.add(User("aaa", "bbb", "abc@iesportada.org"))
            return dataSet
        }

        //se mete la función dentro de companion object, para no tener que crear una instancia
        //de UserRepository, directamente se usa login

        /**
         * La función que se pregunta a Firebase /Room (Sqlite) por el usuario
         */
        suspend fun login(email: String, password: String): Resource {
            //Este código se ejecuta en un hilo específico operaciones entrada/salida (IO)

            //withContext -> al ser lambda, la última linea es el 'result'
            withContext(Dispatchers.IO) {
                delay(1000)
                //Se ejecutará el código de consulta a Firebase que puede tardar más de 5 segundos,
                //y en ese caso se obtiene el error ANR (Android Not Responding) porque puede bloquear la vista.

                //Se puede obtener el error cuando le das a un botón sin parar y a la vez se está
                // esperando a Firebase, y la función no es suspend. La app crashea.
            }
            return Resource.Error(Exception("El password es incorrecto"))
        }

        /**
         * Esta función simula una consulta asincrona y devuelve el conjunto de usuarios de la
         * aplicacion
         */
        suspend fun getUserList(): ResourceList {
            return withContext(Dispatchers.IO) {
                delay(2000)
                when {
                    dataSet.isEmpty() -> ResourceList.Error(Exception("No hay datos"))
                    else -> ResourceList.Success(dataSet as ArrayList<User>)
                }
            }

        }
    }
}
