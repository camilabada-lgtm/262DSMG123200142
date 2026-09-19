fun main() {
    // Ejercicio 2: Impresion de mensajes
    println("Use the val keyword when the value doesn't change.")
    println("Use the var keyword when the value can change.")
    println("When you define a function, you define the parameters that can be passed to it.")
    println("When you call a function, you pass arguments for the parameters.")

    // Ejercicio 4: Plantillas de cadenas
    val discountPercentage = 20
    val item = "Google Chromecast"
    val offer = "Sale - Up to $discountPercentage% discount on $item! Hurry up!"
    println(offer)

    // Ejercicio 5: Concatenacion de cadenas
    val numberOfAdults = 20
    val numberOfKids = 30
    val total = numberOfAdults + numberOfKids
    println("The total party size is: $total")

    // Ejercicio 7: Operaciones matematicas basicas
    val firstNumber = 10
    val secondNumber = 5
    val thirdNumber = 8
    val result = add(firstNumber, secondNumber)
    val anotherResult = subtract(firstNumber, thirdNumber)
    println("$firstNumber + $secondNumber = $result")
    println("$firstNumber - $thirdNumber = $anotherResult")

    // Ejercicio 8: Parametros predeterminados
    println(displayAlertMessage(emailId = "user_one@gmail.com"))
    println(displayAlertMessage("Windows", "user_two@gmail.com"))

    // Ejercicio 9: Podometro
    val steps = 4000
    val caloriesBurned = pedometerStepsToCalories(steps)
    println("Walking $steps steps burns $caloriesBurned calories")

    // Ejercicio 10: Comparacion de dos numeros
    println("Have I spent more time using my phone today: ${compareTime(300, 250)}")

    // Ejercicio 11: Funcion reutilizable para el clima
    printWeatherForCity("Ankara", 27, 31, 82)
    printWeatherForCity("Tokyo", 32, 36, 10)
}

fun add(firstNumber: Int, secondNumber: Int): Int {
    return firstNumber + secondNumber
}

fun subtract(firstNumber: Int, secondNumber: Int): Int {
    return firstNumber - secondNumber
}

fun displayAlertMessage(operatingSystem: String = "Unknown OS", emailId: String): String {
    return "There is a new sign-in request on $operatingSystem for your Google Account $emailId."
}

fun pedometerStepsToCalories(numberOfSteps: Int): Double {
    val caloriesBurnedForEachStep = 0.04
    return numberOfSteps * caloriesBurnedForEachStep
}

fun compareTime(timeSpentToday: Int, timeSpentYesterday: Int): Boolean {
    return timeSpentToday > timeSpentYesterday
}

fun printWeatherForCity(cityName: String, lowTemp: Int, highTemp: Int, chanceOfRain: Int) {
    println("City: $cityName")
    println("Low temperature: $lowTemp, High temperature: $highTemp")
    println("Chance of rain: $chanceOfRain%")
    println()
}