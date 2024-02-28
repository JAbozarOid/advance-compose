package com.example.advancecompose.core.data.datasource.user

import com.example.advancecompose.core.domain.datasource.UserDataSource
import com.example.advancecompose.core.domain.model.user.UserModel
import com.example.advancecompose.core.domain.model.user.UserModelParam
import javax.inject.Inject


//Mock Datasource
class UserDataSourceImpl @Inject constructor() : UserDataSource {
    override suspend fun getUser(): UserModel {
        return mockedUserModel
    }
}

private val mockedUserModel = UserModel.create(
    UserModelParam(
        userNameParam = "eva user"
    )
)