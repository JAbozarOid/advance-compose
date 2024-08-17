package com.example.advancecompose.feature.interview.algoritm


/**
 * Given a string implement a function which capitalizes first letter of every word in that string.
 * capitalizeSentence("flower") // Flower
 * capitalizeSentence("this is a house") // This Is A House
 */
class CapitalizeSentence {

    // idiomatic solution
    fun idiomaticSolution(str: String): String {

        return str.split(" ").joinToString { char ->
            char.replaceFirstChar { it.uppercase() }
        }
    }

    // iterative solution
    fun iterativeSolution(str: String): String {
        val words = mutableListOf<String>()
        str.split(" ").forEach {
            words.add(it[0].uppercase() + it.substring(1))
        }
        return words.joinToString(" ")
    }
}