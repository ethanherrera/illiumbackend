package com.illium.illiumbackend.model

import jakarta.persistence.*

@Entity
data class ClassTechnique(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val status: ClassTechniqueStatus = ClassTechniqueStatus.PENDING,

    @ManyToOne
    @JoinColumn(name = "class_id")
    val gymClass: GymClass,

    @ManyToOne
    @JoinColumn(name = "technique_id")
    val technique: Technique,

    // Additional metadata (e.g., date added, priority, etc.)
    val notes: String? = null
)

enum class ClassTechniqueStatus {
    COMPLETED, PENDING, SKIPPED
}