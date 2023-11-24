package com.example.loginaplication.data.network

/**
 * Esta clase sellada representa la respuesta de un servicio API REST, firebase
 * donde se declara la clase Error que almacenara los errores de la infraestructura,
 * y el caso de éxito sea una coleccion de datos.
 */
sealed class Resource {
    //Ejemplo:
    //data class Success<T,E>(var data: T, var settings: E) : Resource()
    //data class Success<T>(var data: Collection<T>) : Resource() //Collection<T> puede ser o List<String>, o Set<User>, etc


    data class Success<T>(var data: T) : Resource()
    data class Error(var exception: Exception) : Resource()
}