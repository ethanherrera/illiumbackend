package com.illium.illiumbackend.service

import com.illium.illiumbackend.model.Technique
import com.illium.illiumbackend.repository.TechniqueRepository
import org.springframework.stereotype.Service

@Service
class TechniqueService(private val techniqueRepository: TechniqueRepository) {

    fun getAllTechniques(): List<Technique> = techniqueRepository.findAll()

    fun getTechniqueById(id: Long): Technique =
        techniqueRepository.findById(id).orElseThrow { Exception("Technique not found") }

    fun createTechnique(technique: Technique): Technique = techniqueRepository.save(technique)

    fun deleteTechnique(id: Long) = techniqueRepository.deleteById(id)
}
