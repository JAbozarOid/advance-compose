package com.example.advancecompose.feature.interview.kotlinQ

/**
 * Assume that we do not want all of the lambdas passed to an inline function to be inlined,
 * in that case, we can mark those function parameters with the noinline modifier.
 */

inline fun teach(abc: () -> Unit, def: () -> Unit) {
    abc()
    def()
}

fun guide() {
    println("guide start")
    teach(
        { println("teach abc") },
        { println("teach def") }
    )
    println("guide end")
}

// decompile the above code in java
/*public void guide() {
    System.out.print("guide start");
    System.out.print("teach abc");
    System.out.print("teach def");
    System.out.print("guide end");
}*/


inline fun teach2(abc: () -> Unit, noinline def: () -> Unit) {
    abc()
    def()
}

fun guide2() {
    println("guide start")
    teach2(
        { println("teach abc") },
        { println("teach def") }
    )
    println("guide end")
}

//decompile the above code in java
/*public void guide() {
    System.out.print("guide start");
    System.out.print("teach abc");
    teach(new Function() {
        @Override
        public void invoke() {
            System.out.print("teach def");
        }
    });
    System.out.print("guide end");
}

public void teach(Function def) {
    def.invoke();
}*/
