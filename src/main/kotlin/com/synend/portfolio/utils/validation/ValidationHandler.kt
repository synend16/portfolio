package com.synend.portfolio.utils.validation

import com.synend.portfolio.utils.exceptions.UserInputValidationException
import com.synend.portfolio.utils.messages.ExceptionMessages
import com.synend.portfolio.utils.messages.ExceptionMessages.Companion.offsetAndLimitInvalid
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class ValidationHandler{
    companion object {

        fun validateId(paramId: String?, paramName: String?): Long {
            val id: Long

            try {
                id = paramId!!.toLong()
            } catch (e: Exception) {
                val errorMsg: String = if (paramId.equals("undefined")) {
                    ExceptionMessages.missingRequiredField("$paramName")
                } else {
                    ExceptionMessages.invalidIdParameter()
                }
                throw UserInputValidationException(errorMsg)
            }
            return id
        }
        
        fun validateTimeFormat(paramExpireAt: String): String {
            
            val regex = """^(19|20)\d\d[-](0[1-9]|1[012])[-](0[1-9]|[12][0-9]|3[01]) ([01]?[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9]).([0-9]{6})$""".toRegex()
            
            if (regex.matches(paramExpireAt)) {
                return paramExpireAt
            } else {
                throw UserInputValidationException(ExceptionMessages.invalidTimeFormat())
            }
        }

        fun validateDateformat(paramName: String, param: String?): LocalDate {

            val format = "dd-MM-yyyy"

            if (param.isNullOrBlank()){
                throw UserInputValidationException(ExceptionMessages.missingRequiredField(paramName))
            }

            return try {
                LocalDate.parse(param!!, DateTimeFormatter.ofPattern(format))
            } catch (e: Exception){
                throw UserInputValidationException(ExceptionMessages.invalidDateFormat())
            }
        }

        fun validateLimitAndOffset(offset: Int, limit: Int) {
            if(offset < 0 || limit < 1) {
                throw UserInputValidationException(offsetAndLimitInvalid())
            }
        }
    }
}