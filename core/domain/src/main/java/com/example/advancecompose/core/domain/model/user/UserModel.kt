package com.example.advancecompose.core.domain.model.user

class UserModel internal constructor(
    val name: UserNameModel
) {
    companion object {
        fun create(param: UserModelParam): UserModel {
            return UserModel(
                UserNameModel(param.userNameParam)
            )
        }
    }
}

data class UserModelParam(
    val userNameParam: String = ""
)

@JvmInline
value class UserNameModel internal constructor(val value: String) {
    init {
        require(value.isNotEmpty()) { "user name must not be empty" }
    }
}

