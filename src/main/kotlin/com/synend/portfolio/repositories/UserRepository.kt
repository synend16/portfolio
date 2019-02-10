package com.synend.portfolio.repositories

import com.synend.portfolio.models.entities.UserEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: CrudRepository<UserEntity, String> {

}