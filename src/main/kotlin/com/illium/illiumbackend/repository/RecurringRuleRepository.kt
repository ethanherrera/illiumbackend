package com.illium.illiumbackend.repository

import com.illium.illiumbackend.model.RecurringRule
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface RecurringRuleRepository : JpaRepository<RecurringRule, Long> {

}