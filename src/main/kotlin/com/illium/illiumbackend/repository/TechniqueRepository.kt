package com.illium.illiumbackend.repository

import com.illium.illiumbackend.model.Technique
import org.springframework.data.jpa.repository.JpaRepository

interface TechniqueRepository : JpaRepository<Technique, Long> {
    fun findByName(name: String) : List<Technique>
    fun findByDescription(description: String) : List<Technique>
    fun findByLevel(level: Int) : List<Technique>
}