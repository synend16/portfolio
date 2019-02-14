package com.synend.portfolio.controllers

import com.synend.portfolio.models.dtos.auth.AuthenticationDto
import com.synend.portfolio.models.dtos.auth.RegistrationDto
import com.synend.portfolio.services.UserService
import com.synend.portfolio.utils.exceptions.UserInputValidationException
import com.synend.portfolio.utils.logger
import com.synend.portfolio.utils.messages.ExceptionMessages.Companion.missingRequiredField
import com.synend.portfolio.utils.messages.ExceptionMessages.Companion.unauthenticated
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.annotation.*
import java.security.Principal
import javax.servlet.http.HttpSession

@Api(value = "/api/auth", description = "API for authentication")
@RequestMapping(
        path = ["/api/auth"],
        produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
@RestController
@CrossOrigin
class UserController(
        private val authenticationManager: AuthenticationManager,
        private val userDetailsService: UserDetailsService,
        private val userService: UserService){

    val logger = logger<UserController>()

    @ApiOperation("Get user auth")
    @RequestMapping("/user")
    fun user(user: Principal?): ResponseEntity<Map<String, Any>> {
        try {
            val map = mutableMapOf<String,Any>()
            map["name"] = user!!.name
            map["roles"] = AuthorityUtils.authorityListToSet((user as Authentication).authorities)
            return ResponseEntity.ok(map)
        } catch (e : Exception){
            throw UserInputValidationException(unauthenticated(), 401)
        }

    }

    @ApiOperation("Signup a new user")
    @PostMapping(path = ["/signup"],
            consumes = [(MediaType.APPLICATION_JSON_UTF8_VALUE)])
    fun signUp(
            @ApiParam("JSON object representing the registration")
            @RequestBody dto: RegistrationDto)
            : ResponseEntity<Void> {

        validateRegistrationDto(dto)

        val userId : String = dto.username!!
        val password : String = dto.password!!


        val registered = userService.createUser(userId, password, setOf("USER"))

        if (!registered) {
            return ResponseEntity.status(400).build()
        }

        val userDetails = userDetailsService.loadUserByUsername(userId)
        val token = UsernamePasswordAuthenticationToken(userDetails, password, userDetails.authorities)

        authenticationManager.authenticate(token)

        if (token.isAuthenticated) {
            SecurityContextHolder.getContext().authentication = token
        }

        return ResponseEntity.status(204).build()
    }


    @ApiOperation("Make a login")
    @PostMapping(path = ["/login"],
            consumes = [(MediaType.APPLICATION_JSON_UTF8_VALUE)])
    fun login(
            @ApiParam("JSON object representing the authentication")
            @RequestBody dto: AuthenticationDto)
            : ResponseEntity<Void> {

        validateAuthenticationDto(dto)

        val userId : String = dto.username!!
        val password : String = dto.password!!

        val userDetails = try{
            userDetailsService.loadUserByUsername(userId)
        } catch (e: UsernameNotFoundException){
            logger.info("User not found.")
            return ResponseEntity.status(400).build()
        }

        val token = UsernamePasswordAuthenticationToken(userDetails, password, userDetails.authorities)

        authenticationManager.authenticate(token)

        if (token.isAuthenticated) {
            SecurityContextHolder.getContext().authentication = token
            logger.info("User with id: $userId is signed in.")
            return ResponseEntity.status(204).build()
        }

        return ResponseEntity.status(400).build()
    }

    @ApiOperation("Make a logout")
    @PostMapping(path = ["/logout"])
    fun logout(session: HttpSession): ResponseEntity<Void> {
        session.invalidate()
        return ResponseEntity.status(204).build()
    }

    fun validateAuthenticationDto(authenticationDto: AuthenticationDto) {

        when {
            authenticationDto.username.isNullOrBlank() -> handleMissingField("username")
            authenticationDto.password.isNullOrBlank() -> handleMissingField("password")
        }
    }

    fun validateRegistrationDto(registrationDto: RegistrationDto) {
        when {
            registrationDto.password.isNullOrBlank() -> handleMissingField("password")
            registrationDto.username.isNullOrBlank() -> handleMissingField("username")
            registrationDto.firstName.isNullOrBlank() -> handleMissingField("firstName")
            registrationDto.lastName.isNullOrBlank() -> handleMissingField("lastName")
        }
    }

    private fun handleMissingField(fieldName: String){
        val errorMsg = missingRequiredField(fieldName)
        logger.warn(errorMsg)
        throw UserInputValidationException(errorMsg)
    }
}