package com.illium.illiumbackend.service

import com.illium.illiumbackend.model.Technique
import com.illium.illiumbackend.model.TechniqueRequest
import com.illium.illiumbackend.model.toEntity
import com.illium.illiumbackend.repository.TechniqueRepository
import org.springframework.stereotype.Service

@Service
class TechniqueService(private val techniqueRepository: TechniqueRepository) {
    fun createTechnique(techniqueRequest: TechniqueRequest) : Technique {
        val technique = techniqueRequest.toEntity()
        techniqueRepository.save(technique)
        return technique
    }

    fun editTechnique(techniqueRequest: TechniqueRequest, id: Long) : Technique {
        val technique = techniqueRepository.findById(id).orElseThrow {
            Exception("Technique with id $id not found")
        }
        technique.name = techniqueRequest.name
        technique.description = techniqueRequest.description
        techniqueRepository.save(technique)
        return technique
    }

    fun deleteTechnique(id: Long) {
        techniqueRepository.deleteById(id)
    }

    fun getAllTechniques() : List<Technique> {
        return techniqueRepository.findAll()
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
