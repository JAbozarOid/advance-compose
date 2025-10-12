package com.example.advancecompose.feature.interview.designpattern

// ساختار درختی و تکرار شونده

/**
 * Composite structure
 * 1- Component -> class or interface
 * 2- Primitive classes -> these classes implement Component
 * 3- Composite classes -> include component classes
 */

// implement File and Folder with Composite
// 1- Component -> File interface
// 2- Primitive class -> Mp4 which implement File
// 3- Composite Class -> Folder which have Files

interface File {
    val fName: String
    val fSize: Int
}

class Mp4(override val fName: String, override val fSize: Int) : File

class Folder(override val fName: String, files: List<File>) : File {
    override val fSize: Int = files.sumOf { it.fSize }
}

fun filesAndFolders() {
    val project = Folder(
        "Gallery",
        listOf(
            Folder("Summer", listOf(Mp4("tabestoon", 200), Mp4("zedbazi", 400))),
            Mp4("shad", 600),
            Folder("winter", listOf(Mp4("tabestoon", 200), Mp4("zedbazi", 400))),
        )
    )
}

/**
 * A- Composite pattern usage
 *  1- json implementation
 *  2- implement ui
 *      - View (Component)
 *      - TextView (Primitive class)
 *      - ViewGroup (Composite)
 * B- Advantage of Composite
 *      - simplicity in adding new Type
 *
 * C- Disadvantages
 *      - changes one place may change all of the class, for example adding new function in interface (Component)
 *      - for avoiding the above issue we can use visitor pattern
 */