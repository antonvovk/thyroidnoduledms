package com.antonvovk.thyroidnodule.db.users.repositories

import com.antonvovk.thyroidnodule.db.users.models.Permission
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles

@DataJpaTest
@ActiveProfiles("test")
internal class PermissionRepositoryTest @Autowired constructor(
    private val permissionRepository: PermissionRepository
) {

    @Test
    fun `Test save with minimal model`() {
        val permission = Permission(
            name = "VIEW_ANALYSIS"
        )

        val result = permissionRepository.save(permission)
        result shouldBe permission
    }

    @Test
    fun `Test save with full model`() {
        val permission = Permission(
            name = "VIEW_ANALYSIS",
            description = "Some important description for role"
        )

        val result = permissionRepository.save(permission)
        result shouldBe permission
    }
}
