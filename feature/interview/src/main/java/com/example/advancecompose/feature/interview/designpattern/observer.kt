package com.example.advancecompose.feature.interview.designpattern

interface SongObserver {
    fun songUpdated(title: String)
}

class SongObservable {
    private val observers = mutableListOf<SongObserver>()

    fun register(observer: SongObserver) {
        observers.add(observer)
    }

    fun songUpdated(title: String) {
        observers.forEach { observer ->
            observer.songUpdated(title)
        }
    }
}

class MainPagePlayer : SongObserver {

    // this method update ui
    fun showTitle(title: String) {
        // we can show the title here
    }

    override fun songUpdated(title: String) {
         showTitle(title)
    }

}


// at the end -> create instance of the class
val mainPagePlayer = MainPagePlayer()
val songObservable = SongObservable()

fun main() {
    // register the observable in the main activity
    songObservable.register(mainPagePlayer)
}