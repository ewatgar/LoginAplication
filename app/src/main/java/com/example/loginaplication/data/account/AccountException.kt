package com.example.loginaplication.data.account

sealed class AccountException(message: String=""): RuntimeException(message) {
    class InvalidEmailFormat: AccountException("Email con formato inválido")
}