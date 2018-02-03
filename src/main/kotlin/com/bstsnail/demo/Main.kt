package com.bstsnail.demo

import org.apache.commons.exec.CommandLine
import org.apache.commons.exec.DefaultExecutor
import org.apache.commons.exec.LogOutputStream
import org.apache.commons.exec.PumpStreamHandler
import java.io.File

fun main(argv: Array<String>) {
    val cmdLine = CommandLine("echo")
            .addArgument("Hello world!!")
    val executor = DefaultExecutor()
    val outputStream = object: LogOutputStream() {
        override fun processLine(line: String?, level: Int) {
            if (line != null) {
                println(line)
            }
        }
    }
    executor.streamHandler = PumpStreamHandler(outputStream)
    try {
        println("Start to execute the command($cmdLine)")
        executor.workingDirectory = File(".")
        val exitCode = executor.execute(cmdLine)
        if (exitCode != 0) {
            println("Fail to execute the command($cmdLine) with exitCode($exitCode)")
            throw RuntimeException("Fail to execute the command with exitCode($exitCode)")
        }
    }
    catch (t: Throwable) {
        t.printStackTrace()
    }
}