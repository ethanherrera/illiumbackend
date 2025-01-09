package com.illium.illiumbackend.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "event_queues")
data class EventQueue(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    val id: Long = 0L,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    var event: Event,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "queue_id", nullable = false)
    var lessonQueue: LessonQueue,

    @Column(name = "created_at", nullable = false, updatable = false)
    var createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at", nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now()
) {
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

// Request DTO for EventQueue
data class EventQueueRequest(
    val eventId: Long,
    val lessonQueueId: Long
)

// Response DTO for EventQueue
data class EventQueueResponse(
    val id: Long,
    val event: EventResponse,
    val lessonQueue: LessonQueueResponse,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)

// Extension function to map EventQueueRequest to EventQueue
fun EventQueueRequest.toEntity(event: Event, lessonQueue: LessonQueue): EventQueue {
    return EventQueue(
        event = event,
        lessonQueue = lessonQueue
    )
}

// Extension function to map EventQueue to EventQueueResponse
fun EventQueue.toResponse(): EventQueueResponse {
    return EventQueueResponse(
        id = this.id,
        event = this.event.toResponse(),
        lessonQueue = this.lessonQueue.toResponse(),
        createdAt = this.createdAt,
        updatedAt = this.updatedAt
    )
}

