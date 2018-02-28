package com.mfalcier.hashcode

import java.io.File
import java.io.InputStream

fun main(args : Array<String>) {
    val startingTime = System.currentTimeMillis()
    if (args.isEmpty()) {
        println("Please provide a name as a command-line argument")
        return
    }

    print("Converting file into The Matrix... ")
    val matrix = executeCommand { convertFileIntoMatrix(args[0], args[1]) }

    print("Elaborating The Matrix... ")
    val elaboratedMatrix = executeCommand { elaborateMatrix(matrix) }

    print("Writing The Matrix... ")
    executeCommand { convertResultIntoText(elaboratedMatrix, args[0], args[1].split(".")[0]) }

    println("Everything done in ${System.currentTimeMillis()-startingTime}ms.")
}

fun <T> executeCommand(foo: () -> T): T {
    val startingTime = System.currentTimeMillis()
    val result = foo()
    println("Done after ${System.currentTimeMillis()-startingTime}ms.")
    return result
}

fun convertFileIntoMatrix(path: String, file: String): MutableList<MutableList<String>> {
    val superMatrix = mutableListOf<MutableList<String>>()

    val inputStream: InputStream = File("${path}input/$file").inputStream()
    val rows = inputStream.bufferedReader().use { it.readLines() }

    for(row in rows) {
        val newRow = mutableListOf<String>()
        row.toCharArray().mapTo(newRow) { it.toString() }
        superMatrix.add(newRow)
    }

    return superMatrix
}

fun elaborateMatrix(matrix: MutableList<MutableList<String>>): MutableList<MutableList<String>> {
    matrix.removeAt(0)
    return matrix
}

fun convertResultIntoText(matrix: MutableList<MutableList<String>>, path: String, file: String) {
    val output = File("${path}output/$file.out")
    for (row in matrix) {
        var stringRow = ""
        for (element in row) {
            stringRow += "$element "
        }
        output.appendText("$stringRow\n")
    }
}
