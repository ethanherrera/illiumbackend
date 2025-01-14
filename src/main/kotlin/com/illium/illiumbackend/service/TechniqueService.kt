package com.illium.illiumbackend.service

import com.illium.illiumbackend.model.*
import com.illium.illiumbackend.repository.LessonQueueRepository
import com.illium.illiumbackend.repository.LessonQueueTechniqueRepository
import com.illium.illiumbackend.repository.TechniqueRepository
import org.springframework.stereotype.Service

@Service
class TechniqueService(private val techniqueRepository: TechniqueRepository, private val lessonQueueRepository: LessonQueueRepository, private val lessonQueueTechniqueRepository: LessonQueueTechniqueRepository) {
    fun createTechnique(techniqueRequest: TechniqueRequest) : TechniqueResponse {
        val technique = techniqueRequest.toEntity()

        // Add to lesson queue techniques based on level
        val lessonQueues = lessonQueueRepository.findByLevel(technique.level)
        if (lessonQueues.isNotEmpty()) {
            val lessonQueueTechnique = LessonQueueTechnique(
                lessonQueue = lessonQueues[0],
                technique = technique
            )
            lessonQueueTechniqueRepository.save(lessonQueueTechnique)
        }

        techniqueRepository.save(technique)
        return technique.toResponse()
    }

    fun editTechnique(techniqueRequest: TechniqueRequest, id: Long) : TechniqueResponse {
        val technique = techniqueRepository.findById(id).orElseThrow {
            Exception("Technique with id $id not found")
        }
        val oldLevel = technique.level
        technique.name = techniqueRequest.name
        technique.description = techniqueRequest.description
        technique.level = techniqueRequest.level
        techniqueRepository.save(technique)

        // Update lesson queue techniques if level changed
        if (oldLevel != technique.level) {
            val existingLessonQueueTechniques = lessonQueueTechniqueRepository.findAll().filter {
                it.technique.techniqueId == technique.techniqueId
            }
            lessonQueueTechniqueRepository.deleteAll(existingLessonQueueTechniques)

            val newLessonQueues = lessonQueueRepository.findByLevel(technique.level)
            if (newLessonQueues.isNotEmpty()) {
                val newLessonQueueTechnique = LessonQueueTechnique(
                    lessonQueue = newLessonQueues[0],
                    technique = technique
                )
                lessonQueueTechniqueRepository.save(newLessonQueueTechnique)
            }
        }

        return technique.toResponse()
    }

    fun deleteTechnique(id: Long) {
        val technique = techniqueRepository.findById(id).orElseThrow {
            Exception("Technique with id $id not found")
        }

        // Remove from lesson queue techniques
        val associatedLessonQueueTechniques = lessonQueueTechniqueRepository.findAll().filter {
            it.technique.techniqueId == technique.techniqueId
        }
        lessonQueueTechniqueRepository.deleteAll(associatedLessonQueueTechniques)
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
