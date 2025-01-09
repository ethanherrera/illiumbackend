package com.illium.illiumbackend.model

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "techniques")
data class Technique(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "technique_id", nullable = false, unique = true)
    val techniqueId: Long = 0L,

    @Column(name = "name", nullable = false, length = 255)
    var name: String,

    @Column(name = "description", columnDefinition = "TEXT")
    var description: String? = "Add a description here",

    @Column(name = "level", nullable = false)
    var level: Int,

    @Column(name = "created_at", nullable = false, updatable = false)
    var createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at", nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now()
) {
    // Relationship to EventTechnique bridging
    @OneToMany(mappedBy = "technique", cascade = [CascadeType.ALL], orphanRemoval = true)
    var eventTechniques: MutableList<EventTechnique> = mutableListOf()

    // Relationship to LessonQueueTechnique bridging
    @OneToMany(mappedBy = "technique", cascade = [CascadeType.ALL], orphanRemoval = true)
    var lessonQueueTechniques: MutableList<LessonQueueTechnique> = mutableListOf()

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

// Request DTO for Technique
data class TechniqueRequest(
    val name: String,
    val description: String? = null,
    val level: Int
)

// Response DTO for Technique
data class TechniqueResponse(
    val techniqueId: Long,
    val name: String,
    val description: String,
    val level: Int,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)

// Extension function to map TechniqueRequest to Technique
fun TechniqueRequest.toEntity(): Technique {
    return Technique(
        name = this.name,
        description = this.description ?: "Add a description here",
        level = this.level
    )
}

// Extension function to map Technique to TechniqueResponse
fun Technique.toResponse(): TechniqueResponse {
    return TechniqueResponse(
        techniqueId = this.techniqueId,
        name = this.name,
        description = this.description ?: "Add a description here",
        level = this.level,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt
    )
}

