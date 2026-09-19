fun main() {
    // Arrays
    val rockPlanets = arrayOf("Mercury", "Venus", "Earth", "Mars")
    val gasPlanets = arrayOf("Jupiter", "Saturn", "Uranus", "Neptune")
    val solarSystemArray = rockPlanets + gasPlanets
    println("--- Arrays ---")
    for (planet in solarSystemArray) {
        println(planet)
    }
    solarSystemArray[3] = "Little Earth"
    println("Indice 3 actualizado: ${solarSystemArray[3]}")

    // Lists
    println("\n--- Lists ---")
    val solarSystemList = mutableListOf("Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune")
    println("Tamano: ${solarSystemList.size}")
    println("Elemento indice 2: ${solarSystemList[2]}")
    println("Indice de Earth: ${solarSystemList.indexOf("Earth")}")
    println("Indice de Pluto: ${solarSystemList.indexOf("Pluto")}")
    solarSystemList.add("Pluto")
    solarSystemList.add(3, "Theia")
    println("Lista despues de agregar: $solarSystemList")
    solarSystemList.removeAt(9)
    println("Contiene Pluto?: ${solarSystemList.contains("Pluto")}")

    // Sets
    println("\n--- Sets ---")
    val solarSystemSet = mutableSetOf("Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune")
    println("Tamano inicial: ${solarSystemSet.size}")
    solarSystemSet.add("Pluto")
    println("Tamano tras agregar Pluto: ${solarSystemSet.size}")
    solarSystemSet.add("Pluto") // intento de duplicado
    println("Tamano tras agregar Pluto de nuevo: ${solarSystemSet.size}")
    solarSystemSet.remove("Pluto")
    println("Tamano tras quitar Pluto: ${solarSystemSet.size}")

    // Maps
    println("\n--- Maps ---")
    val solarSystemMap = mutableMapOf(
        "Mercury" to 0,
        "Venus" to 0,
        "Earth" to 1,
        "Mars" to 2,
        "Jupiter" to 79,
        "Saturn" to 82,
        "Uranus" to 27,
        "Neptune" to 14
    )
    println("Tamano: ${solarSystemMap.size}")
    solarSystemMap["Pluto"] = 5
    println("Tamano tras agregar Pluto: ${solarSystemMap.size}")
    println("Lunas de Pluto: ${solarSystemMap["Pluto"]}")
    println("Lunas de Theia (no existe): ${solarSystemMap["Theia"]}")
    solarSystemMap.remove("Pluto")
    println("Tamano tras quitar Pluto: ${solarSystemMap.size}")
    solarSystemMap["Jupiter"] = 78
    println("Lunas de Jupiter actualizado: ${solarSystemMap["Jupiter"]}")
}