package com.example.advancecompose.core.ui.model

class UserModel internal constructor(
    val name: UserNameModel
) {
    companion object {
        fun create(username: UserNameParam): UserModel {
            return UserModel(UserNameModel(username))
        }
    }
}

typealias UserNameParam = String

@JvmInline
value class UserNameModel internal constructor(val value: String) {
    init {
        require(value.isNotEmpty()) { "user name must not be empty" }
    }
}