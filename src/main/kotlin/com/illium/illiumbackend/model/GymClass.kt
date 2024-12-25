package com.illium.illiumbackend.model

import jakarta.persistence.*

@Entity
@Table(name = "classes")
data class GymClass(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val name: String,

    @OneToOne
    @JoinColumn(name = "queue_id")
    var queue: Queue = Queue(name="Default Queue")
)