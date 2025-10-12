package com.example.advancecompose.feature.interview.solid.principles

/**
 * We have an Android application that fetches user data from a repository (either from a network or local database)
 * and displays it in a view.
 */

//----------------
/**
 * 1- Domain layer
 * define the entity
 */

// *** region domain layer
data class RealUser(val name: String, val age: Int)

/**
 * GetUserUseCase: This use case is part of the domain layer.
 * It defines the business logic for fetching user data and relies on the UserRepository abstraction to perform its task.
 */
// GetUserUseCase (High-Level Module)
class GetUserUseCase(
    private val userRepository: UserRepository
) {

    fun execute() {
        userRepository.getUser()
    }
}

// UserRepository (Abstraction) -> Abstraction used by GetUserUseCase.
interface UserRepository {
    fun getUser() : RealUser
}
// --- endregion domain

// *** region Data layer
// this is Concrete Classes
// Depends on RemoteDataSource and LocalDataSource abstractions.
class UserRepositoryImpl(
    private val remoteDataSource : RemoteDataSource,
    private val localDataSource : LocalDataSourceImpl
) : UserRepository {
    override fun getUser(): RealUser {
        val realUser = remoteDataSource.fetchUser()
        return realUser
    }
}

// RemoteDataSource (Abstraction) used by UserRepositoryImpl.
interface RemoteDataSource {
    fun fetchUser() : RealUser
}
// LocalDataSource (Abstraction) used by  UserRepositoryImpl.
interface LocalDataSource {
    fun readUser() : RealUser
}

// RemoteDataSourceImpl (Concrete Class)
class RemoteDataSourceImpl : RemoteDataSource {
    override fun fetchUser(): RealUser {
        return RealUser(name = "Abozar", age = 30)
    }

}

// LocalDataSourceImpl (Concrete Class)
class LocalDataSourceImpl : LocalDataSource{
    override fun readUser(): RealUser {
        return RealUser(name = "Abozar", age = 30)
    }

}
// --- endregion data layer

// *** region Presentation layer
/**
 *  high-level modules are the parts of the application that depend on abstractions (interfaces)
 *  rather than concrete implementations
 *  UserViewModel: This class is part of the presentation layer and relies on the GetUserUseCase to fetch user data.
 *  It does not depend on the details of how the data is fetched.
 */

// (High-Level Module)
class UserViewModel (
    private val getUserUseCase: GetUserUseCase
) {

    fun loadUser() {
        val user = getUserUseCase.execute()
    }
}
// --- endregion Presentation layer

/**
 * By following the Dependency Inversion Principle,
 * high-level modules (UserViewModel, GetUserUseCase) depend on abstractions (UserRepository, RemoteDataSource, LocalDataSource),
 * and concrete classes (UserRepositoryImpl, RemoteDataSourceImpl, LocalDataSourceImpl) implement these abstractions.
 * This decouples the high-level modules from the details of the low-level modules, promoting a more modular and maintainable architecture.
 */
