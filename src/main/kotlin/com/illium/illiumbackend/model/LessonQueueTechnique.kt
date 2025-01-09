package com.illium.illiumbackend.model

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "lesson_queue_techniques")
data class LessonQueueTechnique(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    val id: Long = 0L,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "queue_id", nullable = false)
    var lessonQueue: LessonQueue,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "technique_id", nullable = false)
    var technique: Technique,

    @Enumerated(EnumType.ORDINAL) // 0=WAITING, 1=IN_PROGRESS, 2=FINISHED
    @Column(name = "status", nullable = false)
    var status: LessonQueueTechniqueStatus = LessonQueueTechniqueStatus.WAITING,

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

// Request DTO for LessonQueueTechnique
data class LessonQueueTechniqueRequest(
    val lessonQueueId: Long,
    val techniqueId: Long,
    val status: LessonQueueTechniqueStatus = LessonQueueTechniqueStatus.WAITING
)

// Response DTO for LessonQueueTechnique
data class LessonQueueTechniqueResponse(
    val id: Long,
    val lessonQueue: LessonQueueResponse,
    val technique: TechniqueResponse,
    val status: LessonQueueTechniqueStatus,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)

// Extension function to map LessonQueueTechniqueRequest to LessonQueueTechnique
fun LessonQueueTechniqueRequest.toEntity(lessonQueue: LessonQueue, technique: Technique): LessonQueueTechnique {
    return LessonQueueTechnique(
        lessonQueue = lessonQueue,
        technique = technique,
        status = this.status
    )
}

// Extension function to map LessonQueueTechnique to LessonQueueTechniqueResponse
fun LessonQueueTechnique.toResponse(): LessonQueueTechniqueResponse {
    return LessonQueueTechniqueResponse(
        id = this.id,
        lessonQueue = this.lessonQueue.toResponse(),
        technique = this.technique.toResponse(),
        status = this.status,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt
    )
}
