package com.illium.illiumbackend.model

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "event_techniques")
data class EventTechnique(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    val id: Long = 0L,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    var event: Event,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "technique_id", nullable = false)
    var technique: Technique,

    @Enumerated(EnumType.ORDINAL) // stores 0,1,2 if you prefer integer mapping
    @Column(name = "status", nullable = false)
    var status: EventTechniqueStatus = EventTechniqueStatus.WAITING,

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

// Request DTO for EventTechnique
data class EventTechniqueRequest(
    val eventId: Long,
    val techniqueId: Long,
    val status: EventTechniqueStatus = EventTechniqueStatus.WAITING
)

// Response DTO for EventTechnique
data class EventTechniqueResponse(
    val id: Long,
    val event: EventResponse,
    val technique: TechniqueResponse,
    val status: EventTechniqueStatus,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)

// Extension function to map EventTechniqueRequest to EventTechnique
fun EventTechniqueRequest.toEntity(event: Event, technique: Technique): EventTechnique {
    return EventTechnique(
        event = event,
        technique = technique,
        status = this.status
    )
}

// Extension function to map EventTechnique to EventTechniqueResponse
fun EventTechnique.toResponse(): EventTechniqueResponse {
    return EventTechniqueResponse(
        id = this.id,
        event = this.event.toResponse(),
        technique = this.technique.toResponse(),
        status = this.status,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt
    )
}

