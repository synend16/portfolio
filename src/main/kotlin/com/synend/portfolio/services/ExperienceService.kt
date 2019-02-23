package com.synend.portfolio.services

import com.synend.portfolio.models.ExperienceType
import com.synend.portfolio.models.dtos.ExperienceDto
import com.synend.portfolio.repositories.ExperienceRepository
import com.synend.portfolio.utils.converters.ExperienceConverter
import com.synend.portfolio.utils.exceptions.NotFoundException
import com.synend.portfolio.utils.exceptions.UserInputValidationException
import com.synend.portfolio.utils.logger
import com.synend.portfolio.utils.messages.ExceptionMessages
import com.synend.portfolio.utils.messages.InfoMessages
import com.synend.portfolio.utils.validation.ValidationHandler.Companion.validateId
import org.springframework.stereotype.Service

@Service
class ExperienceService(
        private var experienceRepository: ExperienceRepository) {

    val logger = logger<ExperienceService>()

    fun createExperience(dto: ExperienceDto) : ExperienceDto {

        validateExperienceDto(dto)

        dto.title = dto.title!!.capitalize()

        val experience = ExperienceConverter.dtoToEntity(dto)

        val id = experienceRepository.save(experience).id.toString()
        logger.info(InfoMessages.entityCreatedSuccessfully("experience", id))
        return ExperienceDto(id =id)
    }


    fun findExperience(type: ExperienceType?): MutableList<ExperienceDto> {


        val projects = if (type != null){
            experienceRepository.findAllByExperienceType(type)
        } else {
            experienceRepository.findAll()
        }.sortedByDescending { it.endYear }

        return ExperienceConverter.entitiesToDtos(projects)
    }

    fun deleteExperience(stringId: String?) {

        val id = validateId(stringId, "id")

        checkForExperienceInDatabase(id)

        experienceRepository.deleteById(id)
        logger.info(InfoMessages.entitySuccessfullyDeleted("experience", id.toString()))

    }

    private fun checkForExperienceInDatabase(id: Long){
        if (!experienceRepository.existsById(id)){
            val errorMsg = ExceptionMessages.notFoundMessage("Experience", "id", id.toString())
            logger.warn(errorMsg)
            throw NotFoundException(errorMsg)
        }
    }

    private fun handleMissingField(fieldName: String){
        val errorMsg = ExceptionMessages.missingRequiredField(fieldName)
        logger.warn(errorMsg)
        throw UserInputValidationException(errorMsg)
    }

    private fun validateExperienceDto(dto: ExperienceDto) {
        when {
            dto.title.isNullOrEmpty() -> handleMissingField("title")
            dto.description.isNullOrEmpty() -> handleMissingField("description")
            dto.startYear == null -> handleMissingField("startYear")
            dto.endYear == null -> handleMissingField("endYear")
            dto.experienceType == null -> handleMissingField("experienceType")
        }    }
}