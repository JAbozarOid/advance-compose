package com.example.advancecompose.feature.interview.kotlinQ

/**
 * The compiler automatically derives the following members from all properties declared in the primary constructor:
 *
 *  - equals()/.hashCode() pair.
 *  - toString() of the form "User(name=John, age=42)".
 *  - componentN() functions corresponding to the properties in their order of declaration.
 *  - copy() function
 *  - All primary constructor parameters must be marked as val or var.
 */

data class DataClassTest(val name: String, val email: String)