package com.illium.illiumbackend.model

import jakarta.persistence.*

@Entity
data class QueueTechnique(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "queue_id")
    val queue: Queue,

    @ManyToOne
    @JoinColumn(name = "technique_id")
    val technique: Technique,

    val position: Int = 0
)