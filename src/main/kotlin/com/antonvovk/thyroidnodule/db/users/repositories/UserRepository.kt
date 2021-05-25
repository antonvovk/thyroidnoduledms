package com.antonvovk.thyroidnodule.db.users.repositories

import com.antonvovk.thyroidnodule.db.users.models.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {

    fun findByEmail(email: String): User?
}
