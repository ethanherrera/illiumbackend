package com.illium.illiumbackend.repository

import com.illium.illiumbackend.model.ClassTechnique
import org.springframework.data.jpa.repository.JpaRepository

interface ClassTechniqueRepository : JpaRepository<ClassTechnique, Long>