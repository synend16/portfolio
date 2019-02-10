package com.synend.portfolio.utils.cache

import com.synend.portfolio.utils.exceptions.PreconditionException
import com.synend.portfolio.utils.exceptions.UserInputValidationException
import com.synend.portfolio.utils.messages.ExceptionMessages.Companion.missingRequiredHeader
import com.synend.portfolio.utils.messages.ExceptionMessages.Companion.preConditionFailed

class EtagHandler<T> {

    fun generateEtag(dto: T? = null, list: MutableList<T>? = null): String {
        return when {
            dto != null -> dto.hashCode().toString()
            list != null -> list.hashCode().toString()
            else -> throw NullPointerException("Unable to generate eTag, object was empty")
        }
    }

    fun validateEtags(expected: T, request: String?) {
        if (request.isNullOrBlank()){
            throw UserInputValidationException(missingRequiredHeader("If-Match"))
        }

        if (generateEtag(expected) != request!!.replace("\"", "").trim()){
            throw PreconditionException(preConditionFailed())
        }
    }
}