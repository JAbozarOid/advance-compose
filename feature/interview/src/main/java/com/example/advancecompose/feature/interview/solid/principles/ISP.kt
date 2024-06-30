package com.example.advancecompose.feature.interview.solid.principles

/**
 * 1-
 * imagine we have large interface for authentication
 */

interface Authenticator {

    fun authenticateWithEmail(email: String, password: String)
    fun authenticateWithFingerprint()
    fun authenticateWithFacialRecognition()
}

/**
 * 2-
 * Issue with the Large Interface
 * If a class only needs to implement email authentication,
 * it will be forced to provide empty implementations for fingerprint and facial recognition methods,
 * which violates ISP.
 */

/**
 * 3-
 * Applying ISP
 * To adhere to ISP, we should split the Authenticator interface into multiple smaller interfaces.
 */

interface EmailAuthenticator {
    fun authenticateWithEmail(email: String, password: String)
}

interface FingerprintAuthenticator {
    fun authenticateWithFingerprint()
}

interface FacialRecognitionAuthenticator {
    fun authenticateWithFacialRecognition()
}

/**
 * 4-
 * now we can implement relevant interfaces in each class
 */

class EmailAuthenticatorImpl : EmailAuthenticator {
    override fun authenticateWithEmail(email: String, password: String) {
        TODO("Not yet implemented")
    }

}

/**
 * 5-
 * using interfaces
 * Each authentication method can now be used independently,
 */

class AuthService(
    private val emailAuthenticator: EmailAuthenticator,
    private val facialRecognitionAuthenticator: FacialRecognitionAuthenticator,
    private val fingerprintAuthenticator: FingerprintAuthenticator,
) {

    fun authenticateWithEmail(email: String, password: String) {
        emailAuthenticator.authenticateWithEmail(email = email, password = password)
    }

    fun authenticateWithFingerprint() {
        fingerprintAuthenticator.authenticateWithFingerprint()
    }

    fun authenticateWithFacialRecognition() {
        facialRecognitionAuthenticator.authenticateWithFacialRecognition()
    }

}