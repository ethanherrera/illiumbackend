package com.illium.illiumbackend.model

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "lesson_queues")
data class LessonQueue(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "queue_id", nullable = false, unique = true)
    val queueId: Long = 0L,

    @Column(name = "name", nullable = false, length = 255)
    var name: String,

    @Column(name = "level", nullable = false)
    var level: Int,

    @Column(name = "created_at", nullable = false, updatable = false)
    var createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at", nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now()

) {
    @OneToMany(mappedBy = "lessonQueue", cascade = [CascadeType.ALL], orphanRemoval = true)
    var lessonQueueTechniques: MutableList<LessonQueueTechnique> = mutableListOf()

    /**
     * Many-to-many bridging with Event via EventQueue
     */
    @OneToMany(mappedBy = "lessonQueue", cascade = [CascadeType.ALL], orphanRemoval = true)
    var eventQueues: MutableList<EventQueue> = mutableListOf()

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

// Request DTO for LessonQueue
data class LessonQueueRequest(
    val name: String,
    val level: Int,
)

// Response DTO for LessonQueue
data class LessonQueueResponse(
    val queueId: Long,
    val name: String,
    val level: Int,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)

// Extension function to map LessonQueueRequest to LessonQueue
fun LessonQueueRequest.toEntity(): LessonQueue {
    return LessonQueue(
        name = this.name,
        level = this.level,
    )
}

// Extension function to map LessonQueue to LessonQueueResponse
fun LessonQueue.toResponse(): LessonQueueResponse {
    return LessonQueueResponse(
        queueId = this.queueId,
        name = this.name,
        level = this.level,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt
    )
}
