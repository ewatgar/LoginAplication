package com.example.loginaplication.ui.userlist.usercase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginaplication.data.model.User
import com.example.loginaplication.data.network.ResourceList
import com.example.loginaplication.data.repository.UserRepository
import kotlinx.coroutines.launch

class UserListViewModel:ViewModel() {
    //no se puede cambiar el valor, sólo recogerlo
    private var state = MutableLiveData<UserListState>()

    fun getState():LiveData<UserListState>{
        return state
    }

    /**
     * Funcion que pide el listado de usuarios al repositorio
     */
    fun getUserList(){
        //Iniciar corrutina
        viewModelScope.launch {
            state.value = UserListState.Loading(true)
            val result=UserRepository.getUserList()
            state.value = UserListState.Loading(false)
            //Opcion 1: me devuelve diferentes estados
            when(result){
                is ResourceList.Success<*> -> state.value = UserListState.Success(result.data as ArrayList<User>)
                is ResourceList.Error -> state.value = UserListState.NoDataError
            }

            //Opcion 2: me devuelve la lista, porque solo tenemos 2 posibles estados, el de error y el de éxito
            //esta forma es mala cuando hay más de un tipo de error
            /*
            val data = UserRepository.dataSet.getUserList()
            when{
                data.isEmpty() -> state.value = UserListState.NoDataError
                else -> state.value = UserListState.Success(data)
            }*/
        }
    }

}