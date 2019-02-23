package com.synend.portfolio.services

import com.synend.portfolio.models.GitHubRepositoryDto
import com.synend.portfolio.models.dtos.ProjectDto
import com.synend.portfolio.utils.exceptions.InternalException
import com.synend.portfolio.utils.logger
import com.synend.portfolio.utils.messages.ExceptionMessages
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.*
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

    private final val acceptHeader= "application/vnd.github.mercy-preview+json"
    val logger = logger<GitHubService>()


    fun getProjects(): List<GitHubRepositoryDto> {

        val headers = HttpHeaders()
        headers.add("Accept", acceptHeader)

        val response : ResponseEntity<List<GitHubRepositoryDto>> = try {
            restTemplate.exchange(
                    githubUrl,
                    HttpMethod.GET,
                    HttpEntity(null, headers),
                    object : ParameterizedTypeReference<List<GitHubRepositoryDto>>() {})

        } catch (e : HttpClientErrorException) {
            logger.error("client error when accessing github: {}", e.message)
            throw e
        } catch (e : HttpServerErrorException){
            logger.warn("Github is not available: {}", e.message)
            throw e
        }


        if (response.body == null){
            logger.error("Empty response body from github")
            throw InternalException("INTERNAL ERROR")
        }

        response.body!!.forEach { validateGithubRepository(it) }

        return response.body!!
    }


    fun filterExistingProjectsAndMapThem(response: List<GitHubRepositoryDto>): List<ProjectDto>
    {
        return response
                .filter { !projectService.existsByUrl(it.svn_url!!) }
                .map { githubRepositoryToProjectDto(it) }
    }


    private fun handleMissingField(fieldName: String){
        val errorMsg = ExceptionMessages.missingRequiredField(fieldName)
        logger.warn(errorMsg)
        throw InternalException("INTERNAL ERROR")
    }


    private fun validateGithubRepository(response: GitHubRepositoryDto) {
        when {
            response.description.isNullOrBlank() -> response.description = ""
            response.name.isNullOrBlank() -> handleMissingField("name")
            response.id.isNullOrBlank() -> handleMissingField("id")
            response.svn_url.isNullOrBlank() -> handleMissingField("svn_url")
            response.pushed_at.isNullOrBlank() -> handleMissingField("pushed_at")
            response.topics == null -> response.topics = mutableListOf()
        }
    }


    private fun githubRepositoryToProjectDto(repositoryDto: GitHubRepositoryDto): ProjectDto {

        val formattedTime = repositoryDto.pushed_at!!
                .replace("T", " ")
                .replace("Z", "")

        return ProjectDto(
                title = repositoryDto.name,
                lastUpdated = formattedTime,
                url = repositoryDto.svn_url,
                topics = repositoryDto.topics!!.toMutableSet(),
                description = repositoryDto.description
        )
    }
}