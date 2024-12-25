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
    private val queueService: QueueService,
    private val techniqueService: TechniqueService,
    private val queueTechniqueService: QueueTechniqueService
) {

    fun getAllClasses(): List<GymClass> = gymClassRepository.findAll()

    fun getClassById(id: Long): GymClass =
        gymClassRepository.findById(id).orElseThrow { Exception("Class not found") }

    fun createClass(gymClass: GymClass): GymClass {
        val savedQueue = queueService.createQueue(Queue(name="{gymClass.name queue}"))
        val techniques = techniqueService.getAllTechniques()
        val queueTechniques = techniques.mapIndexed {
            index, technique ->
            QueueTechnique(
                queue = savedQueue,
                technique = technique,
                position = index + 1
            )
        }
        queueTechniqueService.saveAllQueueTechniques(queueTechniques)
        val queueClass: GymClass = gymClass.copy(queue = savedQueue)
        return gymClassRepository.save(queueClass)



    }

    fun createDefaultClass(gymClass: GymClass): GymClass = gymClassRepository.save(gymClass)

    fun deleteClass(id: Long) = gymClassRepository.deleteById(id)
}