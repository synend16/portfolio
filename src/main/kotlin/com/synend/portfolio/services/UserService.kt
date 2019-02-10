package com.synend.portfolio.services

import com.synend.portfolio.models.entities.UserEntity
import com.synend.portfolio.repositories.UserRepository
import com.synend.portfolio.utils.exceptions.ConflictException
import com.synend.portfolio.utils.logger
import com.synend.portfolio.utils.messages.ExceptionMessages.Companion.usernameInUse
import com.synend.portfolio.utils.messages.InfoMessages.Companion.entityCreatedSuccessfully
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class UserService(
        private val userRepository: UserRepository,
        private val passwordEncoder: PasswordEncoder

) {

    val logger = logger<UserService>()

    fun createUser(username: String, password: String, roles: Set<String> = setOf()) : Boolean{

        try {
            val hash = passwordEncoder.encode(password)

            if (userRepository.existsById(username)) {
                logger.info(usernameInUse())
                throw ConflictException(usernameInUse())
            }

            val user = UserEntity(username, hash, roles.map{"ROLE_$it"}.toSet())

            userRepository.save(user)
            logger.info(entityCreatedSuccessfully("User", user.username!!))

            return true
        } catch (e: Exception){
            logger.error(e.message)
            return false
        }
    }

}