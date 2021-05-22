package com.antonvovk.ThyroidNoduleDMS.db.users.repositories

import com.antonvovk.ThyroidNoduleDMS.db.users.models.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long>
