package com.example.advancecompose.feature.interview.generics

/**
 * In Kotlin, "covariant," "contravariant," and "invariant" are terms that relate to the type system, particularly when dealing with generics
 */

/**
 * Covariant :
 * - Covariance allows you to use a more specific type in place of a more general type
 * - if Derived is a subtype of Base, then List<Derived> is considered a subtype of List<Base>. This means you can use a List<Derived> wherever a List<Base> is expected
 * - Covariance is denoted by using the "out" keyword.
 */

open class Animal {
    override fun toString(): String {
        return "Animal"
    }
}

class Dog : Animal() {
    override fun toString(): String {
        return "Dog"
    }
}

// input parameters accept every entity in kind of Animal
fun feedAnimals(animal: List<out Animal>) {
    println("dogs food")
}

val dogs: List<Dog> = listOf(Dog(), Dog())

/**
 * Contravariant :
 * - Contravariance is the opposite of covariance. It allows you to use a more general type in place of a more specific type
 * - Contravariance is denoted by using the "in" keyword.
 */

interface Printer<in T> {
    fun print(item: T)
}

class AnimalPrinter : Printer<Animal> {
    override fun print(item: Animal) {
        println("Printing animals $item")
    }
}

class DogPrinter : Printer<Dog> {
    override fun print(item: Dog) {
        println("Printing dogs: $item")
    }
}

fun printAll(printer: Printer<Animal>, items: List<Animal>) {
    items.forEach {
        printer.print(it)
    }
}

/**
 * Invariant means that the relationship between two types is fixed and cannot be changed.
 * This means that List<Derived> is not considered a subtype or supertype of List<Base>.
 * In Kotlin, generic types are invariant by default.
 */
class Box<T>(var item: T)
fun <T> swapBoxes(box1: Box<T>, box2: Box<T>) {
    val temp = box1.item
    box1.item = box2.item
    box2.item = temp
}


fun main() {
    // func feedAnimals just accept the type derived from Animal
    //region covariant
    feedAnimals(dogs)
    //endregion

    //region contravariant
    val animalPrinter: Printer<Animal> = AnimalPrinter()
    val dogPrinter: Printer<Dog> = DogPrinter()
    val animals: List<Animal> = listOf(Animal(), Dog())

    printAll(animalPrinter, animals)
    //printAll(dogPrinter, animals)
    //endregion

    //region invariant
    val box1 = Box(5)
    val box2 = Box(10)

    println("Before swap: box1 = ${box1.item}, box2 = ${box2.item}")

    swapBoxes(box1, box2)

    println("After swap: box1 = ${box1.item}, box2 = ${box2.item}")
    //endregion
}


