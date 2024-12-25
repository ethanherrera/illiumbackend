package com.illium.illiumbackend.service

import com.illium.illiumbackend.model.*
import com.illium.illiumbackend.repository.GymClassRepository
import com.illium.illiumbackend.repository.QueueRepository
import com.illium.illiumbackend.repository.QueueTechniqueRepository
import com.illium.illiumbackend.repository.TechniqueRepository
import org.springframework.stereotype.Service
import kotlin.math.min

@Service
class GymClassService(
    private val gymClassRepository: GymClassRepository,
    private val queueService: QueueService,
    private val techniqueService: TechniqueService,
    private val queueTechniqueService: QueueTechniqueService,
    private val classTechniqueService: ClassTechniqueService
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
            )
        }
        queueTechniqueService.saveAllQueueTechniques(queueTechniques)
        val queueClass: GymClass = gymClass.copy(queue = savedQueue)
        return gymClassRepository.save(queueClass)



    }

    fun createDefaultClass(gymClass: GymClass): GymClass = gymClassRepository.save(gymClass)

    fun deleteClass(id: Long) = gymClassRepository.deleteById(id)

    fun dequeTechniques(id: Long, amount: Int): List<Technique> {
        val gymClass = getClassById(id)
        val queue = gymClass.queue ?: throw Exception("Class with id: $id does not contain a queue")
        val queueTechniques = queueTechniqueService.findTechniquesByQueueId(queue.id)
        if (queueTechniques.isEmpty()) {
            throw Exception("No techniques available in the queue for class with ID: $id")
        }
        val amountToTake = min(queueTechniques.size, amount)
        val techniquesToDequeue = queueTechniques.take(amountToTake)
        techniquesToDequeue.forEach { queueTechnique ->
            queueTechniqueService.deleteQueueTechnique(queueTechnique.id)
        }
        val classTechniques = techniquesToDequeue.map { queueTechnique ->
            ClassTechnique(
                gymClass = gymClass,
                status = ClassTechniqueStatus.PENDING,
                technique = queueTechnique.technique,
                notes = "Dequeued from queue"
            )
        }
        classTechniqueService.saveAllClassTechnique(classTechniques)
        return classTechniques.map { it.technique }
    }

    fun completeTechnique(id: Long): ClassTechnique {
        val classTechnique = classTechniqueService.getClassTechniqueById(id)
        val classTechniqueCopy = classTechnique.copy(status = ClassTechniqueStatus.COMPLETED)
        return classTechniqueService.saveClassTechnique(classTechniqueCopy)
    }

    fun skipTechnique(id: Long): ClassTechnique {
        val classTechnique = classTechniqueService.getClassTechniqueById(id)
        val classTechniqueCopy = classTechnique.copy(status = ClassTechniqueStatus.SKIPPED)
        queueTechniqueService.saveQueueTechnique(
            QueueTechnique(
                queue = classTechnique.gymClass.queue,
                technique = classTechnique.technique
            )
        )
        return classTechniqueService.saveClassTechnique(classTechniqueCopy)
    }
}