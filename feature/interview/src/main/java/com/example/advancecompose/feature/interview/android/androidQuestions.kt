package com.example.advancecompose.feature.interview.android

/**
 * 1- Four Essential states of the Activity are
 *      A- Active/Running
 *      B- Paused
 *      C- Stopped
 *      D- Destroyed
 *
 * 2- What is content provider and what is it typically used for
 *      - Is a component that enables apps ti manage and share data securely with other apps
 *      - data sharing : share data between different applications
 *      - CRUD operations
 *      - Accessing Contacts
 *      - Media Access
 *      - Custom Data
 *
 * 3- what is instance variable
 *      - are variables that declared in a class and are specific to instances of that class
 *      - scope : accessible within all the methods of the class they are declared
 *      - lifecycle : variables is tied to the object of the class
 *      - Access Modifier : private, protected and public
 *
 * 4- what is explicit typecasting
 *      - it's the process where the programmer manually converts a variable from one data type to another
 *      - implicit typecasting is when conversion is automatically handled by the compiler
 *      - int myInt = 100 -> double myDouble = (double) myInt
 *
 * 5- what is string pool
 *      - in java is a special memory area in the java heap where String objects are stored
 *
 * 6- Methods of the class thread in Java which stop threads
 *      - Thread.interrupt()
 *      - Thread.stop()
 *
 * 7- What is the primary purpose of the annotation in Kotlin
 *      - used to attach metadata to classes, functions, properties, parameters or expressions
 *      - used by compiler, tools, or at runtime to modify behavior or provide additional information
 *      - usages :
 *          - code metadata
 *              - @JvmStatic
 *          - interoperability
 *              - @JvmOverloads
 *          - custom annotation
 *          - framework and libraries
 *              - @Inject dependency inject
 *          - runtime processing
 * 8- differences between Backing field and backing property
 *          - backing field
 *              - is a private field that kotlin automatically generates for a property if that property needs to store a value
 *              - use getter and setter method to store property value
 *              - "field" keyword stores the actual value
 *          - backing property
 *              - is a property which you manually define to back another property
 *              - when you need to store additional data alongside a property
 *              - use when to expose a public property with custom logic but store the actual value in a separate private property
 */

class BackingField {

    // define a backing field
    var name : String = "Abozar Raghibdoust"
        get() = field.uppercase()
        set(value) {
            field = value.trim()
        }

    // backing property
    private var _age : Int = 0
    var age : Int
        get() = _age
        set(value) {
            if (value > 0) _age = value
        }
}

fun main() {
    val backingField : BackingField = BackingField()
    println("the name declared in class is ${backingField.name}")
    backingField.name = "Amir Mohammad"
    println("the name changed is ${backingField.name}")

    println("the age is ${backingField.age}")
    backingField.age = -1
    println("the age is ${backingField.age}")
    backingField.age = 10
    println("the age is ${backingField.age}")

}

/**
 * 9- which elements can be contained within kotlin classes
 *      - val / var properties
 *      - methods
 *      - constructors
 *      - init block
 *      - nested class
 *      - companion objects
 *      - data classes
 *      - enum class
 *      - sealed classes
 *      - interfaces
 *      - type aliases
 */