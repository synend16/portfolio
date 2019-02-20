package com.synend.portfolio.services

import com.synend.portfolio.models.GitHubRepository
import com.synend.portfolio.models.GitHubRepositoryResponse
import com.synend.portfolio.models.dtos.ProjectDto
import com.synend.portfolio.utils.exceptions.InternalException
import com.synend.portfolio.utils.logger
import com.synend.portfolio.utils.messages.ExceptionMessages
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.HttpServerErrorException
import org.springframework.web.client.RestTemplate

@Service
class GitHubService(
        private var restTemplate: RestTemplate,
        private var projectService: ProjectService) {


    @Value("\${githubUrl}")
    private lateinit var githubUrl : String

    val logger = logger<GitHubService>()


    fun getProjects(): GitHubRepositoryResponse {


        val response : ResponseEntity<GitHubRepositoryResponse> = try {
            restTemplate.getForEntity(githubUrl, GitHubRepositoryResponse::class.java)
        } catch (e : HttpClientErrorException) {
            logger.error("client error when accessing github: {}", e.message)
            throw e
        } catch (e : HttpServerErrorException){
            logger.warn("Github is not available: {}", e.message)
            throw e
        }


        if (response.body == null || response.body!!.list == null){
            logger.error("Empty response body from github")
            throw InternalException("INTERNAL ERROR")
        }

        response.body!!.list!!.forEach { validateGithubRepository(it) }


        return response.body!!
    }


    fun filterExisitingProjectsAndMapThem(response: GitHubRepositoryResponse): List<ProjectDto>
    {
        return response.list!!
                .filter { projectService.existsByUrl(it.svn_url!!) }
                .map { githubRepositoryToProjectDto(it) }
    }


    private fun handleMissingField(fieldName: String){
        val errorMsg = ExceptionMessages.missingRequiredField(fieldName)
        logger.warn(errorMsg)
        throw InternalException("INTERNAL ERROR")
    }


    private fun validateGithubRepository(response: GitHubRepository) {
        when {
            response.description.isNullOrBlank() -> response.description = ""
            response.name.isNullOrBlank() -> handleMissingField("name")
            response.id.isNullOrBlank() -> handleMissingField("id")
            response.svn_url.isNullOrBlank() -> handleMissingField("svn_url")
            response.updated_at.isNullOrBlank() -> handleMissingField("updated_at")
            response.topics == null -> response.topics = mutableListOf()
        }
    }


    private fun githubRepositoryToProjectDto(repository: GitHubRepository): ProjectDto {
        return ProjectDto(
                title = repository.name,
                lastUpdated = repository.updated_at,
                url = repository.svn_url,
                topics = repository.topics!!.toMutableSet(),
                description = repository.description
        )
    }
}