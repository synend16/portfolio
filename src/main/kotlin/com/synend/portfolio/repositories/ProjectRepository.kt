package com.synend.portfolio.repositories

import com.synend.portfolio.models.entities.ProjectEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProjectRepository: CrudRepository<ProjectEntity, Long> {

    fun findAllByTopicsContainsIgnoreCase(topics: String): Iterable<ProjectEntity>

    fun findAllByTopics(topics: String): Iterable<ProjectEntity>

}