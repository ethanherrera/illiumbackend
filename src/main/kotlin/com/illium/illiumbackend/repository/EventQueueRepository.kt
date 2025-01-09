package com.illium.illiumbackend.repository

import com.illium.illiumbackend.model.EventQueue
import org.springframework.data.jpa.repository.JpaRepository

interface EventQueueRepository : JpaRepository<EventQueue, Long>