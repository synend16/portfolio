package com.synend.portfolio.utils.converters

import com.synend.portfolio.models.dtos.ProjectDto
import com.synend.portfolio.models.entities.ProjectEntity

class ProjectConverter {

    companion object {

        fun entityToDto(project: ProjectEntity): ProjectDto {
            return ProjectDto(
                    project.id.toString(),
                    project.url,
                    project.title,
                    project.topics!!.toMutableSet(),
                    project.description
            )
        }

        fun dtoToEntity(projectDto: ProjectDto): ProjectEntity {
            val entity = ProjectEntity(
                    url = projectDto.url,
                    title = projectDto.title,
                    description = projectDto.description
            )

            if (entity.topics != null){
                entity.topics = projectDto.topics
            }

            return entity
        }

        fun entityToDto(projectDtos: Iterable<ProjectEntity>) : MutableList<ProjectDto> {
            return projectDtos.map { entityToDto(it) }.toMutableList()
        }
    }
}