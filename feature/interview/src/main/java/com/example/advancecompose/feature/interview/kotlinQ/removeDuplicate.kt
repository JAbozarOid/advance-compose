package com.example.advancecompose.feature.interview.kotlinQ

/**
 * remove duplicate from an array
 * there are four function to do this
 * 1- distinct()
 * 2- toSet()
 * 3- toMutableSet()
 * 4- toHashSet()
 */

data class Mentors(val id: Int, val name: String)

val mentors = arrayOf(
    Mentors(id = 0, name = "a"),
    Mentors(id = 1, name = "b"),
    Mentors(id = 2, name = "o"),
    Mentors(id = 3, name = "z"),
    Mentors(id = 4, name = "a"),
    Mentors(id = 4, name = "r"),
    Mentors(id = 4, name = "r"),
    Mentors(id = 4, name = "a"),
    Mentors(id = 4, name = "g"),
    Mentors(id = 4, name = "h"),
    Mentors(id = 4, name = "i"),
    Mentors(id = 4, name = "b"),
    Mentors(id = 4, name = "d"),
    Mentors(id = 4, name = "o"),
    Mentors(id = 4, name = "u"),
    Mentors(id = 4, name = "s"),
    Mentors(id = 4, name = "t"),
)

/**
 * remove duplicate with distinct()
 * 1- maintain the original order of items in result
 * 2- it's extension function on array
 * 3- return list
 */
val removeWithDictinct = mentors.distinct()


/**
 * remove duplicate with toSet()
 * 1- maintain the original order of items in result
 * 2- return set which is read only, it means can not perform add on toSet()
 */
val removeWithToSet = mentors.toSet()

/**
 * remove duplicate with toMutableSet()
 * 1- maintain the original order of items in result
 * 2- Returns MutableSet which is a read/write set it means can not perform add on
 */
val removeWithToMutableSet = mentors.toMutableSet()

/**
 * remove duplicate with toHashSet()
 * 1- similar to mutableSet do not maintain original order
 * 2- return hashSet
 */
val removeWithToHashSet = mentors.toHashSet()


fun main() {
    println("the original array with duplicate items are")
    mentors.forEach {
        println(it.name)
    }
    println("remove duplicate from the above array")
    println("remove duplicated with distinct() $removeWithDictinct")
    println("remove duplicated with toSet() $removeWithToSet")
    println("remove duplicated with toMutableSet() $removeWithToMutableSet")
    println("remove duplicated with toHashSet() $removeWithToHashSet")
}