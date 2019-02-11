package com.synend.portfolio.utils.converters

import com.synend.portfolio.utils.exceptions.UserInputValidationException
import com.synend.portfolio.utils.messages.ExceptionMessages
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class ConventionHandler {

    companion object {

        // Converting string to ZonedDateTime
        // inspired by this answer from StackOverflow
        // https://stackoverflow.com/a/44487882/10396560
        fun convertTimeStampToZonedTimeDate(timestamp: String): ZonedDateTime? {

            val pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS"
            val parser: DateTimeFormatter = DateTimeFormatter.ofPattern(pattern).withZone(ZoneId.systemDefault())

            return try {

                ZonedDateTime.parse(timestamp, parser)

            } catch (e: Exception) {

                throw UserInputValidationException(ExceptionMessages.invalidTimeFormat())

            }
        }

    }

}