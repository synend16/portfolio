package com.synend.portfolio.controllers

import com.synend.portfolio.models.ExperienceType
import com.synend.portfolio.models.dtos.ExperienceDto
import com.synend.portfolio.models.dtos.ResponseDto
import com.synend.portfolio.models.dtos.WrappedResponse
import com.synend.portfolio.services.ExperienceService
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

@Api(value = "/api/experiences", description = "API for my projects")
@RequestMapping(
        path = ["/api/experiences"],
        produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
@RestController
@CrossOrigin
class ExperienceController(
        private var experienceService: ExperienceService) {


    @ApiOperation("Getting experiences, possible filter by type")
    @GetMapping
    fun findExperiences(@ApiParam("Experience Type")
                     @RequestParam("type", required = false)
                     type : ExperienceType?,

                     @ApiParam("Offset in the list of experiences")
                     @RequestParam("offset", defaultValue = "0")
                     offset: Int,

                     @ApiParam("Limit of experiences in a single retrieved page")
                     @RequestParam("limit", defaultValue = "10")
                     limit: Int): ResponseEntity<WrappedResponse<ExperienceDto>> {
        val experiences = experienceService.findExperience(type)

        val builder = UriComponentsBuilder.fromPath("/experiences")
        if (type != null) {
            builder.queryParam("type", type)
        }

        val pageDto = PageDtoGenerator<ExperienceDto>().generatePageDto(experiences, offset, limit)
        return HalLinkGenerator<ExperienceDto>().generateHalLinks(experiences, pageDto, builder, limit, offset)
    }


    @ApiOperation("Create an experience")
    @PostMapping(consumes = ["application/json"])
    fun createExperience(
            @ApiParam("JSON object representing the Experience")
            @RequestBody experienceDto: ExperienceDto): ResponseEntity<WrappedResponse<ExperienceDto>> {
        val dto = experienceService.createExperience(experienceDto)

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .location(URI.create("/experiences/${dto.id}"))
                .body(
                        ResponseDto(
                                code = HttpStatus.CREATED.value(),
                                page = PageDto(mutableListOf(dto))
                        ).validated()
                )
    }

    @ApiOperation("Delete an Experience")
    @DeleteMapping(path = ["/{id}"], consumes = ["application/json"])
    fun deleteProject(

            @ApiParam("The id of the Experience")
            @PathVariable("id")
            id: String?): ResponseEntity<Void> {
        experienceService.deleteExperience(id)

        return ResponseEntity.noContent().build()

    }
}