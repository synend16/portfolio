package com.synend.portfolio.utils.hal

import com.synend.portfolio.models.dtos.ResponseDto
import com.synend.portfolio.utils.exceptions.UserInputValidationException
import com.synend.portfolio.models.dtos.WrappedResponse
import com.synend.portfolio.utils.validation.ValidationHandler
import com.synend.portfolio.utils.cache.EtagHandler
import com.synend.portfolio.utils.messages.ExceptionMessages
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.util.UriComponentsBuilder

class HalLinkGenerator<T> {



    fun generateHalLinks(totalList: MutableList<T>, pageDto: PageDto<T>, builder: UriComponentsBuilder, limit: Int, offset: Int): ResponseEntity<WrappedResponse<T>>{

        ValidationHandler.validateLimitAndOffset(offset, limit)

        builder.queryParam("limit", limit)

        if (offset != 0 && offset >= totalList.size) {
            throw UserInputValidationException(ExceptionMessages.tooLargeOffset(offset))
        }

        // Build HalLinks
        pageDto._self = HalLink(builder.cloneBuilder()
                .queryParam("offset", offset)
                .build().toString()
        )

        if (!totalList.isEmpty() && offset > 0) {
            pageDto.previous = HalLink(builder.cloneBuilder()
                    .queryParam("offset", Math.max(offset - limit, 0))
                    .build().toString()
            )
        }

        if (offset + limit < totalList.size) {
            pageDto.next = HalLink(builder.cloneBuilder()
                    .queryParam("offset", (offset + limit))
                    .build().toString()
            )
        }

        val etag = EtagHandler<T>().generateEtag(list = totalList)

        return ResponseEntity.status(HttpStatus.OK)
                .eTag(etag)
                .body(ResponseDto(
                        code = HttpStatus.OK.value(),
                        page = pageDto
                ).validated()
                )
    }

}