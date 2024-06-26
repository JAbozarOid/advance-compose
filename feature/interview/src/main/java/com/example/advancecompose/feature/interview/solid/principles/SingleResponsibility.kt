package com.example.advancecompose.feature.interview.solid.principles

import java.io.File

/**
 * User profile manager class
 */

//region without SRP
class UserProfileManager {

    /**
     * a class with 3 different jobs
     */

    fun displayUserInfo(): User {
        return User(id = 0, name = "Abozar")
    }

    fun saveUserSetting(userSettings: UserSettings) {
        userSettings.isNotificationActive = false
    }

    fun uploadProfilePicture(file: File) {

    }
}
//endregion

//region applied SRP on UserProfileManager class
class UserInfoDisplayManager {
    fun displayUserInfo(): User {
        return User(id = 0, name = "Abozar")
    }
}

class UserSettingsManager {
    fun saveUserSetting(userSettings: UserSettings) {
        userSettings.isNotificationActive = false
    }
}

class ProfilePictureUploader {
    fun uploadProfilePicture(file: File) {

    }
}
//endregion


data class User(val id: Int, val name: String)
data class UserSettings(var isNotificationActive: Boolean)

/**
 * Benefits of Following SRP
 * Maintainability: Each class has a clear responsibility, making the code easier to maintain.
 * Testability: Smaller classes with single responsibilities are easier to test.
 * Scalability: Changes in one responsibility don’t affect others, making the codebase more scalable.
 * Reusability: Classes with single responsibilities are more likely to be reused in different parts of the application or in other projects.
 */