package com.example.advancecompose.feature.interview.kotlinQ

fun main() {

    val name = "Abozar"
    val family = "Raghib"
    val githubId = "jabozaroid"
    val list = listOf<String>()

    val buildStr = buildString {
        append(name)
        append(family)
        append(githubId)
        list.forEach {
            appendLine()
            append(it)
        }

    }

    buildList<Int> {
        add(2)
    }

    println(buildStr)


    val characters = "ABCDEF" + "GHIJKLMN" + "OPQRSTUVWXYZ"

    // ways for printing letters
    // 1-
    characters.forEach {
        println(it)
    }

    // 2-
    // A= 65 code ascii
    (65..65 + 25).forEach {
        println(it.toChar())
    }

    //3-
    CharRange('A', 'Z').forEach {
        println(it)
    }

    //4-
    val bigStr = "ABCDEFsdkfjdsofj3fmvdw391121sdspk}{}{DS}DAS}FF@)))" + "GHIJKLMN" + "OPQRSTUVWXYZ"
    bigStr.toCharArray().filter {
        it.isLetter()
    }.sorted().distinct() // distinct remove repeated
        .map { it.uppercase() }.forEach {
            println(it)
        }


}