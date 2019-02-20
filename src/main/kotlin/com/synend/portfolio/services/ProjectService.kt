package com.synend.portfolio.services

import com.synend.portfolio.models.dtos.ProjectDto
import com.synend.portfolio.repositories.ProjectRepository
import com.synend.portfolio.utils.converters.ProjectConverter
import com.synend.portfolio.utils.exceptions.NotFoundException
import com.synend.portfolio.utils.exceptions.UserInputValidationException
import com.synend.portfolio.utils.logger
import com.synend.portfolio.utils.messages.ExceptionMessages
import com.synend.portfolio.utils.messages.ExceptionMessages.Companion.illegalParameter
import com.synend.portfolio.utils.messages.ExceptionMessages.Companion.missingRequiredField
import com.synend.portfolio.utils.messages.ExceptionMessages.Companion.unableToParse
import com.synend.portfolio.utils.messages.InfoMessages.Companion.entityCreatedSuccessfully
import org.springframework.stereotype.Service

@Service
class ProjectService(
        private var projectRepository: ProjectRepository) {

    val logger = logger<ProjectService>()


    fun createProject(dto: ProjectDto) : ProjectDto {

        validateProjectDto(dto)

        dto.title = dto.title!!.capitalize()

        val project = ProjectConverter.dtoToEntity(dto)

        val id = projectRepository.save(project).id.toString()
        logger.info(entityCreatedSuccessfully("project", id))
        return ProjectDto(id =id)
    }

    fun findProject(topic: String?): MutableList<ProjectDto> {


        val projects = if (!topic.isNullOrBlank()){
            projectRepository.findAllByTopics(topic!!)
        } else {
            projectRepository.findAll()
        }.sortedByDescending { it.lastUpdated }

        return ProjectConverter.entityToDto(projects)
    }

    fun existsByUrl(url: String): Boolean {
        return projectRepository.existsByUrl(url)
    }


    private fun handleUnableToParse(fieldName: String){
        val errorMsg = unableToParse(fieldName)
        logger.warn(errorMsg)
        throw UserInputValidationException(errorMsg)
    }

    private fun handleIllegalField(fieldName: String){
        val errorMsg = illegalParameter(fieldName)
        logger.warn(errorMsg)
        throw UserInputValidationException(errorMsg)
    }

    private fun handleMissingField(fieldName: String){
        val errorMsg = missingRequiredField(fieldName)
        logger.warn(errorMsg)
        throw UserInputValidationException(errorMsg)
    }

    private fun checkForProjectInDatabase(id: Long){
        if (!projectRepository.existsById(id)){
            val errorMsg = ExceptionMessages.notFoundMessage("Project", "id", id.toString())
            logger.warn(errorMsg)
            throw NotFoundException(errorMsg)
        }
    }

    fun validateProjectDto(projectDto: ProjectDto) {
        when {
            projectDto.url.isNullOrEmpty() -> handleMissingField("url")
            projectDto.title.isNullOrEmpty() -> handleMissingField("title")
            projectDto.topics == null -> handleMissingField("topics")
            projectDto.topics!!.isEmpty() -> handleMissingField("topics")
            projectDto.description.isNullOrEmpty() -> handleMissingField("description")
            projectDto.lastUpdated == null -> handleMissingField("lastUpdated")
        }
    }
}