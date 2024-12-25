package com.illium.illiumbackend.repository

import com.illium.illiumbackend.model.QueueTechnique
import org.springframework.data.jpa.repository.JpaRepository

interface QueueTechniqueRepository : JpaRepository<QueueTechnique, Long>