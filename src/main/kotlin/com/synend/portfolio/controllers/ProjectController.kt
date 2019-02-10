package com.synend.portfolio.controllers

import com.synend.portfolio.models.dtos.ProjectDto
import com.synend.portfolio.models.dtos.ResponseDto
import com.synend.portfolio.models.dtos.WrappedResponse
import com.synend.portfolio.services.ProjectService
import com.synend.portfolio.utils.hal.HalLinkGenerator
import com.synend.portfolio.utils.hal.PageDto
import com.synend.portfolio.utils.hal.PageDtoGenerator
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import java.net.URI

@Api(value = "/projects", description = "API for movie entity")
@RequestMapping(
        path = ["/projects"],
        produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
@RestController
@CrossOrigin
class ProjectController(
        private var projectService: ProjectService
) {

    @ApiOperation("Getting projects, possible filter by topic")
    @GetMapping
    fun findProjects(@ApiParam("Topic")
                     @RequestParam("topic", required = false)
                     topic : String?,

                     @ApiParam("Offset in the list of projects")
                     @RequestParam("offset", defaultValue = "0")
                     offset: Int,

                     @ApiParam("Limit of projects in a single retrieved page")
                     @RequestParam("limit", defaultValue = "10")
                     limit: Int): ResponseEntity<WrappedResponse<ProjectDto>> {
        val projects = projectService.findProject(topic)

        val builder = UriComponentsBuilder.fromPath("/projects")
        if (!topic.isNullOrEmpty()) {
            builder.queryParam("topic", topic)
        }

        val pageDto = PageDtoGenerator<ProjectDto>().generatePageDto(projects, offset, limit)
        return HalLinkGenerator<ProjectDto>().generateHalLinks(projects, pageDto, builder, limit, offset)
    }


    @ApiOperation("Create a Project")
    @PostMapping(consumes = ["application/json"])
    fun createMovie(
            @ApiParam("JSON object representing the Project")
            @RequestBody projectDto: ProjectDto): ResponseEntity<WrappedResponse<ProjectDto>> {
        val dto = projectService.createProject(projectDto)

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .location(URI.create("/projects/${dto.id}"))
                .body(
                        ResponseDto(
                                code = HttpStatus.CREATED.value(),
                                page = PageDto(mutableListOf(dto))
                        ).validated()
                )
    }

}