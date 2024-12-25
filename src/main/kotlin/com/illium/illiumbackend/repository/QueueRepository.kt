package com.illium.illiumbackend.repository

import com.illium.illiumbackend.model.Queue
import org.springframework.data.jpa.repository.JpaRepository

interface QueueRepository : JpaRepository<Queue, Long>