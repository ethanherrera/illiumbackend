package com.illium.illiumbackend.service

import com.illium.illiumbackend.model.GymClass
import com.illium.illiumbackend.model.Queue
import com.illium.illiumbackend.model.QueueTechnique
import com.illium.illiumbackend.repository.GymClassRepository
import com.illium.illiumbackend.repository.QueueRepository
import com.illium.illiumbackend.repository.QueueTechniqueRepository
import com.illium.illiumbackend.repository.TechniqueRepository
import org.springframework.stereotype.Service

@Service
class GymClassService(
    private val gymClassRepository: GymClassRepository,
    private val queueRepository: QueueRepository,
    private val techniqueRepository: TechniqueRepository,
    private val queueTechniqueRepository: QueueTechniqueRepository
) {

    fun getAllClasses(): List<GymClass> = gymClassRepository.findAll()

    fun getClassById(id: Long): GymClass =
        gymClassRepository.findById(id).orElseThrow { Exception("Class not found") }

    fun createClass(gymClass: GymClass): GymClass = gymClassRepository.save(gymClass)

    fun createDefaultClass(gymClass: GymClass): GymClass {
        val savedClass = gymClassRepository.save(gymClass)

        // Create a queue
        val queue = Queue(name = "${gymClass.name} Queue")
        val savedQueue = queueRepository.save(queue)

        // Retrieve all techniques from the database
        val techniques = techniqueRepository.findAll()

        // Populate the queue with all techniques
        val queueTechniques = techniques.mapIndexed { index, technique ->
            QueueTechnique(queue = savedQueue, technique = technique, position = index + 1)
        }
        queueTechniqueRepository.saveAll(queueTechniques)

        // Associate the queue with the class
        savedClass.queue = savedQueue
        return gymClassRepository.save(savedClass)
    }

    fun deleteClass(id: Long) = gymClassRepository.deleteById(id)
}