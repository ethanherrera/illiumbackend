package com.illium.illiumbackend.repository

import com.illium.illiumbackend.model.LessonQueueTechnique
import org.springframework.data.jpa.repository.JpaRepository

interface LessonQueueTechniqueRepository : JpaRepository<LessonQueueTechnique, Long> {
}