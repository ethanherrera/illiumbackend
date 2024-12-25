package com.illium.illiumbackend.repository

import com.illium.illiumbackend.model.GymClass
import org.springframework.data.jpa.repository.JpaRepository

interface GymClassRepository : JpaRepository<GymClass, Long>