package com.illium.illiumbackend.service

import com.illium.illiumbackend.model.ClassTechnique
import com.illium.illiumbackend.repository.ClassTechniqueRepository
import org.springframework.stereotype.Service

@Service
class ClassTechniqueService(
    private val classTechniqueRepository: ClassTechniqueRepository
) {

    fun getAllClassTechniques(): List<ClassTechnique> = classTechniqueRepository.findAll()

    fun getClassTechniqueById(id: Long): ClassTechnique =
        classTechniqueRepository.findById(id).orElseThrow { Exception("ClassTechnique not found") }

    fun createClassTechnique(ct: ClassTechnique): ClassTechnique = classTechniqueRepository.save(ct)

    fun deleteClassTechnique(id: Long) = classTechniqueRepository.deleteById(id)
}