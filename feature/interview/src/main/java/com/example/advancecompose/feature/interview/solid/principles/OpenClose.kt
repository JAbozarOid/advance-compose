package com.example.advancecompose.feature.interview.solid.principles

import java.io.FileWriter

/**
 * the good example of this principle is RecyclerView.Adapter, we can extend the behavior but we can not modify the parent class
 * we want to have a logger class to logs messages to console
 */

// 1-
class Logger {

    fun log(message: String) {
        println(message)
    }
}

// 2- we want to extend this class to log also in a file -> without modifying the existing Logger class
// To adhere to the OCP, you can achieve this by using inheritance or composition.
// A- define a logger interface
interface ILogger {
    fun log(msg: String)
}

// B- implement the console logger
class ConsoleLogger : ILogger {
    override fun log(msg: String) {
        println(
            msg
        )
    }
}

// C- implement the file logger
class FileLogger : ILogger {

    private lateinit var filePath: String

    private fun fileLogger(filePath: String) {
        this.filePath = filePath
    }

    override fun log(msg: String) {
        try {
            val writer: FileWriter = FileWriter(filePath, true)
            writer.write(msg)
        } catch (e: Exception) {
            println(e.message)
        }
    }

}

/**
 * Now, instead of modifying the original Logger class,
 * you can extend the functionality by using different implementations of the ILogger interface.
 */
class Application(private val logger: ILogger) {
    fun performTask() {
        // Perform some task
        logger.log("Task performed")
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val consoleLogger: ILogger = ConsoleLogger()
            val appWithConsoleLogger = Application(consoleLogger)
            appWithConsoleLogger.performTask()

            val fileLogger: ILogger = FileLogger()
            val appWithFileLogger = Application(fileLogger)
            appWithFileLogger.performTask()
        }
    }
}

/**
 * Benefits of Following OCP
 * Extensibility: You can add new logging mechanisms (e.g., database logging, remote server logging) without modifying the existing code.
 * Maintainability: Reduces the risk of introducing bugs when extending functionality.
 * Flexibility: Different parts of the application can use different logging mechanisms as needed.
 * By following the Open/Closed Principle in this way, you ensure that your code is more robust, flexible, and easier to extend as new requirements arise, without the need to alter existing, tested, and stable code
 */

