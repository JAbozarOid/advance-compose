package com.example.advancecompose.feature.interview.solid

/**
 * solid principle
 *      1- single responsibility
 *          - a class should only have one job
 *          - one reason to change
 *      2- open-closed
 *          - open for extension, close for modification
 *          - if new functionality needs to be added, it should be added to an extension of the class
 *      3- liskov substitution
 *          - low level classes can be substituted without affecting higher levels
 *          - achieved using abstract classes and interfaces
 *      4- interface segregation
 *          - use interface to advertise functionality
 *          - many specific interfaces are better than one generic interface
 *          - an interface only exposes the methods that the dependent class needs not more
 *      5- dependency inversion
 *          - concrete classes depend on abstract classes not the other way around
 *          - framework specific functionality depends on business logic
 */