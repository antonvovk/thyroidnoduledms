package com.antonvovk.thyroidnodule.db.users.repositories

import com.antonvovk.thyroidnodule.db.users.models.Permission
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PermissionRepository : JpaRepository<Permission, Long>
