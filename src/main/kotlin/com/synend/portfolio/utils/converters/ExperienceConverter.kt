package com.synend.portfolio.utils.converters

import com.synend.portfolio.models.dtos.ExperienceDto
import com.synend.portfolio.models.entities.ExperienceEntity
import com.synend.portfolio.utils.validation.ValidationHandler

class ExperienceConverter {

    companion object {

        fun entityToDto(entity: ExperienceEntity): ExperienceDto {
            return ExperienceDto(
                    entity.id.toString(),
                    entity.title,
                    entity.description,
                    entity.startYear!!.year.toString(),
                    entity.endYear!!.year.toString(),
                    entity.experienceType
            )
        }

        fun dtoToEntity(dto: ExperienceDto): ExperienceEntity {
            return ExperienceEntity(
                    title = dto.title,
                    description = dto.description,
                    startYear = ValidationHandler.validateDateformat("startYear", dto.startYear),
                    endYear = ValidationHandler.validateDateformat("startYear", dto.endYear),
                    experienceType = dto.experienceType
            )
        }

        fun entitiesToDtos(entities: Iterable<ExperienceEntity>): MutableList<ExperienceDto> {
            return entities.map { entityToDto(it) }.toMutableList()
        }
    }
}