package com.synend.portfolio.services

import com.synend.portfolio.models.dtos.ProjectDto
import com.synend.portfolio.models.entities.ProjectEntity
import com.synend.portfolio.repositories.ProjectRepository
import com.synend.portfolio.utils.converters.ProjectConverter
import com.synend.portfolio.utils.converters.ProjectConverter.Companion.dtoToEntity
import com.synend.portfolio.utils.exceptions.NotFoundException
import com.synend.portfolio.utils.exceptions.UserInputValidationException
import com.synend.portfolio.utils.logger
import com.synend.portfolio.utils.messages.ExceptionMessages
import com.synend.portfolio.utils.messages.ExceptionMessages.Companion.illegalParameter
import com.synend.portfolio.utils.messages.ExceptionMessages.Companion.invalidParameter
import com.synend.portfolio.utils.messages.ExceptionMessages.Companion.missingRequiredField
import com.synend.portfolio.utils.messages.ExceptionMessages.Companion.unableToParse
import com.synend.portfolio.utils.messages.InfoMessages.Companion.entityCreatedSuccessfully
import com.synend.portfolio.utils.messages.InfoMessages.Companion.entitySuccessfullyDeleted
import com.synend.portfolio.utils.messages.InfoMessages.Companion.entitySuccessfullyUpdated
import com.synend.portfolio.utils.validation.ValidationHandler.Companion.validateId
import org.springframework.stereotype.Service

@Service
class ProjectService(
        private var gitHubService: GitHubService,
        private var projectRepository: ProjectRepository) {

    val logger = logger<ProjectService>()


    fun createProject(dto: ProjectDto) : ProjectDto {

        validateProjectDto(dto)

        dto.title = splitWord(dto.title!!)

        val project = dtoToEntity(dto)

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


    fun getProject(stringId: String?): ProjectEntity {
        val id = validateId(stringId, "id")

        checkForProjectInDatabase(id)

        return projectRepository.findById(id).get()
    }


    fun updateProject(stringId: String?, projectDto: ProjectDto) {
        val id = validateId(stringId, "id")

        validateProjectDto(projectDto)
        if (projectDto.id.isNullOrEmpty()){
            handleMissingField("id")
        }

        if (!stringId.equals(projectDto.id)){
            val errorMsg = invalidParameter(stringId!!, projectDto.id!!)
            logger.warn(errorMsg)
            throw UserInputValidationException(errorMsg)
        }

        val projectEntity = getProject(stringId)

        val entity = dtoToEntity(projectDto)
        entity.id = id
        projectRepository.save(entity)
        logger.info(entitySuccessfullyUpdated("Project", projectEntity.id.toString()))
    }


    fun deleteProject(stringId: String?) {
        val id =validateId(stringId, "id")

        checkForProjectInDatabase(id)

        projectRepository.deleteById(id)
        logger.info(entitySuccessfullyDeleted("project", id.toString()))
    }

    private fun splitWord(word: String): String {
        word.capitalize()
        word.replace("_", " ")
        word.replace("-", " ")

        return word
    }

    fun refresh() {
        projectRepository.deleteAll()
        logger.info("Successfully deleted all projects")

        val gitHubRepositories = gitHubService.getProjects()

        val projects = gitHubService.formatGithubResponse(gitHubRepositories)

        projects.forEach { createProject(it) }
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
            projectDto.lastUpdated == null -> handleMissingField("lastUpdated")
        }
    }


}