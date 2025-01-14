package com.illium.illiumbackend.service

import com.illium.illiumbackend.model.*
import com.illium.illiumbackend.repository.TechniqueRepository
import org.springframework.stereotype.Service

@Service
class TechniqueService(private val techniqueRepository: TechniqueRepository) {
    fun createTechnique(techniqueRequest: TechniqueRequest) : TechniqueResponse {
        val technique = techniqueRequest.toEntity()
        techniqueRepository.save(technique)
        return technique.toResponse()
    }

    fun editTechnique(techniqueRequest: TechniqueRequest, id: Long) : TechniqueResponse {
        val technique = techniqueRepository.findById(id).orElseThrow {
            Exception("Technique with id $id not found")
        }
        technique.name = techniqueRequest.name
        technique.description = techniqueRequest.description
        technique.level = techniqueRequest.level
        techniqueRepository.save(technique)
        return technique.toResponse()
    }

    fun deleteTechnique(id: Long) {
        techniqueRepository.deleteById(id)
    }

    fun getAllTechniques() : List<TechniqueResponse> {
        return techniqueRepository.findAll().map { it.toResponse() }
    }

    fun getTechniquesByName(name: String) : List<Technique> {
        return techniqueRepository.findByName(name)
    }

    fun getTechniquesByDescription(description: String) : List<Technique> {
        return techniqueRepository.findByDescription(description)
    }

    fun getTechniquesByLevel(level: Int) : List<Technique> {
        return techniqueRepository.findByLevel(level)
    }
}
