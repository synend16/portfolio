package com.synend.portfolio.utils.schedule

import com.synend.portfolio.services.GitHubService
import com.synend.portfolio.services.ProjectService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class ProjectsScheduler(
        private var gitHubService: GitHubService,
        private var projectService: ProjectService
) {

    @Scheduled(fixedRate = 1000 * 60 * 60 * 60)
    fun updateProjects(){

        val gitHubRepositories = gitHubService.getProjects()

        gitHubRepositories.forEach { println(it.topics!!.size) }

        val projects = gitHubService.filterExisitingProjectsAndMapThem(gitHubRepositories)

        println(projects.size)

        projects.forEach { projectService.createProject(it) }
    }
}