package com.illium.illiumbackend.repository

import com.illium.illiumbackend.model.EventTechnique
import org.springframework.data.jpa.repository.JpaRepository

interface EventTechniqueRepository : JpaRepository<EventTechnique, Long>