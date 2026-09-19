/*
 * Este programa demuestra el uso de variables,
 * tipos de datos basicos y comentarios en Kotlin.
 */
fun main() {
    // Variable mutable de tipo Int
    var count = 10
    println("You have $count unread messages.")

    // Incrementa y decrementa el contador
    count++
    println("You have $count unread messages.")
    count--
    println("You have $count unread messages.")

    // Variables Double para sumar distancias de un viaje
    val trip1 = 3.20
    val trip2 = 4.10
    val trip3 = 1.72
    val totalTripLength = trip1 + trip2 + trip3
    println("$totalTripLength miles left to destination")

    // Concatenacion de Strings
    val nextMeeting = "Next meeting: "
    val date = "January 1"
    val reminder = nextMeeting + date + " at work"
    println(reminder)

    // Variable Boolean
    val notificationsEnabled = false
    println("Are notifications enabled? " + notificationsEnabled)
}