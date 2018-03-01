package com.athnn.hashcode

import com.athnn.hashcode.model.*
import java.io.File
import java.io.InputStream

/**
 * Main function, passing a path and the filename
 */
fun main(args: Array<String>) {

    val startingTime = System.currentTimeMillis()
    if (args.isEmpty()) {
        println("Please provide a name as a command-line argument")
        return
    }

    val files = listOf<String>("a_example.in", "b_should_be_easy.in", "c_no_hurry.in", "d_metropolis.in", "e_high_bonus.in")

    for (file in files) {
        print("Converting $file into The Matrix... ")
        val configuration = executeCommand { convertFileIntoConfiguration(args[0], file) }

        print("Elaborating... ")
        val vehicles = executeCommand { elaborateConfiguration(configuration) }

        print("Writing into file... ")
        executeCommand { convertVehiclesIntoFile(vehicles, args[0], file.split(".")[0]) }

        println("$file completed!")
    }
    println("Everything done in ${System.currentTimeMillis() - startingTime}ms.")
}

/**
 * Given a function, it will execute it and print the execution time
 */
fun <T> executeCommand(foo: () -> T): T {
    val startingTime = System.currentTimeMillis()
    val result = foo()
    println("Done after ${System.currentTimeMillis() - startingTime}ms.")
    return result
}

/**
 * Given a path and a filename, it will convert it into a Configuration
 */
fun convertFileIntoConfiguration(path: String, file: String): Configuration {
    val inputStream: InputStream = File("${path}input/$file").inputStream()
    var rows = inputStream.bufferedReader().use { it.readLines() }

    // GETTING CITY
    val firstSplittedRow = rows[0].split(" ")
    val defaultVeichle = Vehicle(Point(0,0))
    val nVehicles = firstSplittedRow[2].toInt()
    val vehicles = Vehicles(nVehicles)
    val city = City(firstSplittedRow[1].toLong(), firstSplittedRow[0].toLong())

    // GETTING bonus and maxSteps
    val bonus = firstSplittedRow[4].toLong()
    val maxSteps = firstSplittedRow[5].toLong()

    // GETTING RIDES
    val listRides = mutableListOf<Ride>()
    for ((rideId, row) in rows.withIndex()) {
        if (rideId != 0) {
            val splittedRow = row.split(" ")
            val ride = Ride(rideId.toLong() - 1, Point(splittedRow[0].toLong(), splittedRow[1].toLong()), Point(splittedRow[2].toLong(), splittedRow[3].toLong()), splittedRow[4].toLong(), splittedRow[5].toLong())
            listRides.add(ride)
        }
    }
    val rides = Rides(listRides)

    return Configuration(city, bonus, rides, maxSteps, vehicles)
}

/**
 * Given a matrix, it will process it and return another matrix
 */
fun elaborateConfiguration(configuration: Configuration): Vehicles {
    for (step in 0L..configuration.maxSteps) {
        configuration.vehicles.waitingVehicles.assignRides(configuration, step)

        configuration.vehicles.notifyStep(step)
    }

    return configuration.vehicles
}

/**
 * Given a matrix, a path and a filename, it will print it into a file
 */
fun convertVehiclesIntoFile(vehicles: Vehicles, path: String, file: String) {
    val output = File("${path}output/$file.out")
    for (vehicle in vehicles.vehicles) {
        var stringRow = "${vehicle.completedRides.rides.size} "
        for (ride in vehicle.completedRides.rides) {
            stringRow += "${ride.id} "
        }
        output.appendText("$stringRow\n")
    }
}
