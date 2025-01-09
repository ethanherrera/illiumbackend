package com.illium.illiumbackend.model

import jakarta.persistence.*
import org.slf4j.event.Level
import java.time.LocalDateTime

@Entity
@Table(name = "events")
data class Event(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id", nullable = false, unique = true)
    val eventId: Long = 0L,

    @Column(name = "name", nullable = false, length = 255)
    var name: String,

    @Column(name = "description", columnDefinition = "TEXT")
    var description: String? = "Add a description here",

    @Column(name = "start_time", nullable = false)
    var startTime: LocalDateTime,

    @Column(name = "end_time", nullable = false)
    var endTime: LocalDateTime,

    @Column(name = "is_recurring", nullable = false)
    var isRecurring: Boolean,

    @OneToOne(cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name="rule_id")
    var recurringRule: RecurringRule? = null, // Relationship to RecurringRule

    @Column(name = "level", nullable = false, updatable = false)
    var level: Int,

    @Column(name = "created_at", nullable = false, updatable = false)
    var createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at", nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now()
) {

    // Many-to-many with LessonQueue via EventQueue
    @OneToMany(mappedBy = "event", cascade = [CascadeType.ALL], orphanRemoval = true)
    var eventQueues: MutableList<EventQueue> = mutableListOf()

    // Many-to-many with Technique via EventTechnique
    @OneToMany(mappedBy = "event", cascade = [CascadeType.ALL], orphanRemoval = true)
    var eventTechniques: MutableList<EventTechnique> = mutableListOf()

    @PrePersist
    fun onCreate() {
        createdAt = LocalDateTime.now()
        updatedAt = LocalDateTime.now()
    }

    @PreUpdate
    fun onUpdate() {
        updatedAt = LocalDateTime.now()
    }
}

// Request DTO for Event
data class EventRequest(
    val name: String,
    val description: String? = null,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
    val isRecurring: Boolean,
    val recurringRule: RecurringRuleRequest? = null,
    val level: Int,
)

// Response DTO for Event
data class EventResponse(
    val eventId: Long,
    val name: String,
    val description: String,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
    val isRecurring: Boolean,
    val recurringRule: RecurringRuleResponse?,
    val level: Int,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)

// Extension function to map EventRequest to Event
fun EventRequest.toEntity(): Event {
    return Event(
        name = this.name,
        description = this.description ?: "Add a description here",
        startTime = this.startTime,
        endTime = this.endTime,
        isRecurring = this.isRecurring,
        recurringRule = this.recurringRule?.toEntity(),
        level = this.level
    )
}

// Extension function to map Event to EventResponse
fun Event.toResponse(): EventResponse {
    return EventResponse(
        eventId = this.eventId,
        name = this.name,
        description = this.description ?: "Add a description here",
        startTime = this.startTime,
        endTime = this.endTime,
        isRecurring = this.isRecurring,
        recurringRule = this.recurringRule?.toResponse(),
        level = this.level,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt
    )
}
