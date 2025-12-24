package com.example.advancecompose.feature.interview.oop

/**
 * class objects are created in heap memory
 * structure objects are created in stack memory
 */

/**
 * A- encapsulation
 * 1- public
 * 2- protected : access from derived class --> same package or sub-class
 * 3- private
 */

/**
 * B- Abstraction
 * hide unnecessary data from the user
 * all operation on ATM machine like withdraw are hidden, actually users don't know how it works
 */

/**
 * C- Inheritance
 */

/**
 * D- Polymorphism
 * ability of an object to take on many forms
 * 1- compile time
 *      - function overloading
 *      - operator overloading
 * 2- runtime
 *      - virtual functions
 */

/**
 * static function : can be called without creating an object
 */

/**
 * difference between Shallow copy and Deep copy
 * both of them have important role in copying objects in Prototype design pattern
 * 1- Shallow copy :
 *      - it will create a new object from the existing object and then copying value type fields of the current object to the new object
 *      - in the case of reference type, it will copy the reference not the referred object itself
 *      - in the below example abozar1 and abozar2 fields are copied and stored in a different memory location but reference type field position still pointing to the same old memory location
 *      - if any changes to position object happened both abozar1 and abozar2 objects will affect.
 * 2- Deep Copy
 *      - it will create a new object from the existing object and then copying the fields of the current object to the newly created object
 *      - if the field is a value type then a bit-by-bit copy of the filed will be performed
 *      - if the field is a reference type then a new copy of the reference object is created
 */
data class Abozar(
    var age: String,
    var position: Position,
)

data class Position(
    var info: String
)

val abozar1 = Abozar(age = "30", position = Position(info = "Android Dev"))
val abozar2 = Abozar(age = "38", position = Position(info = "Senior Android Dev"))