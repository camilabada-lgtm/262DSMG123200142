data class Event(
    val title: String,
    val description: String? = null,
    val daypart: Daypart,
    val durationInMinutes: Int,
)

enum class Daypart {
    MORNING,
    AFTERNOON,
    EVENING,
}

val Event.durationOfEvent: String
    get() = if (this.durationInMinutes < 60) {
        "short"
    } else {
        "long"
    }

fun main() {
    // Tarea 1: crear una instancia de Event
    val studyEvent = Event(
        title = "Study Kotlin",
        description = "Commit to studying Kotlin at least 15 minutes per day.",
        daypart = Daypart.EVENING,
        durationInMinutes = 15
    )
    println(studyEvent)

    // Tarea 3: lista mutable de eventos
    val event1 = Event(title = "Wake up", description = "Time to get up", daypart = Daypart.MORNING, durationInMinutes = 0)
    val event2 = Event(title = "Eat breakfast", daypart = Daypart.MORNING, durationInMinutes = 15)
    val event3 = Event(title = "Learn about Kotlin", daypart = Daypart.AFTERNOON, durationInMinutes = 30)
    val event4 = Event(title = "Practice Compose", daypart = Daypart.AFTERNOON, durationInMinutes = 60)
    val event5 = Event(title = "Watch latest DevBytes video", daypart = Daypart.AFTERNOON, durationInMinutes = 10)
    val event6 = Event(title = "Check out latest Android Jetpack library", daypart = Daypart.EVENING, durationInMinutes = 45)

    val events = mutableListOf(event1, event2, event3, event4, event5, event6)
    println("Total events: ${events.size}")

    // Tarea 4: filtrar eventos cortos
    val shortEvents = events.filter { it.durationInMinutes < 60 }
    println("You have ${shortEvents.size} short events.")

    // Tarea 5: agrupar por franja horaria
    val groupedEvents = events.groupBy { it.daypart }
    groupedEvents.forEach { (daypart, eventsInGroup) ->
        println("$daypart: ${eventsInGroup.size} events")
    }

    // Tarea 6: ultimo evento del dia
    println("Last event of the day: ${events.last().title}")

    // Tarea 7: propiedad de extension durationOfEvent
    println("Duration of first event of the day: ${events[0].durationOfEvent}")
}