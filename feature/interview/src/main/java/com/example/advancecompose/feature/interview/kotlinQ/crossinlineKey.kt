package com.example.advancecompose.feature.interview.kotlinQ

/**
 * crossinline in Kotlin is used to avoid non-local returns
 */

inline fun teach(abc: () -> Unit) {
    abc()
}

fun guide3() {
    println("guide start")
    teach {
        println("teach abc")
        return // allowed
    }
    println("guide end")
}

// decompile the above code in Java
/*
public void guide() {
    System.out.print("guide start");
    System.out.print("teach abc");

   // *** there is no System.out.print("guide end")

}*/

inline fun teach2(crossinline abc: () -> Unit) {
    abc()
}

fun guide4() {
    println("guide start")
    teach2 {
        println("teach abc")
        // return // not allowed
    }
    println("guide end")
}
