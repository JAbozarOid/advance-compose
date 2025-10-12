package com.example.advancecompose.feature.interview.test

/**
 * Fake and Mock objects are both used to replace real implementations
 *
 * A- Usage
 *  - Fake Test
 *      - Fakes are commonly used when you want to test multiple components together (not just unit tests) without depending on real external systems (e.g., databases or network services).
 *      - If you want to simulate a lightweight version of a class’s real behavior, such as using an in-memory database instead of an actual database during testing.
 *  - Mock Test
 *      - Mocks are used primarily to verify interactions with a class’s dependencies.
 *      - Unit Tests
 */

/**
 * Example
 * - Fake Test
 *   -In a banking app,
 *   instead of using the actual database for tests,
 *   you could use an in-memory database Fake to simulate real data operations.
 */

data class User(
    val id: Int,
    val name: String
)

interface UserRepository {
    fun addUser(user: User)
    fun getUser(id: Int): User?
}

class FakeUserRepository : UserRepository {

    private val users = mutableListOf<User>()
    override fun addUser(user: User) {
        users.add(user)
    }

    override fun getUser(id: Int): User? {
        return users.find {
            it.id == id
        }
    }


}