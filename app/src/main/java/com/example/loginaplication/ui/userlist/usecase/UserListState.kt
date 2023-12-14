package com.example.loginaplication.ui.userlist.usecase

import com.example.loginaplication.data.model.User

sealed class UserListState {
    data object NoDataError : UserListState()
    data class Success(val dataset: ArrayList<User>) : UserListState()
    data class Loading(val value: Boolean) : UserListState()
}
