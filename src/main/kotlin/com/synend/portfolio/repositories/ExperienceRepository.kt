package com.synend.portfolio.repositories

import com.synend.portfolio.models.ExperienceType
import com.synend.portfolio.models.entities.ExperienceEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ExperienceRepository: CrudRepository<ExperienceEntity, Long> {

    fun findAllByExperienceType(experienceType: ExperienceType): Iterable<ExperienceEntity>
}