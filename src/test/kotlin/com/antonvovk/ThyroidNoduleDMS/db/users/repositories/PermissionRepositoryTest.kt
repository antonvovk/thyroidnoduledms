package com.antonvovk.ThyroidNoduleDMS.db.users.repositories

import com.antonvovk.ThyroidNoduleDMS.db.users.models.Permission
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
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
        assertSoftly(result) {
            id shouldNotBe null
            name shouldBe permission.name
            description shouldBe null
            created shouldNotBe null
            updated shouldNotBe null
        }
    }

    @Test
    fun `Test save with full model`() {
        val permission = Permission(
            name = "VIEW_ANALYSIS",
            description = "Some important description for role"
        )

        val result = permissionRepository.save(permission)
        assertSoftly(result) {
            id shouldNotBe null
            name shouldBe permission.name
            description shouldBe permission.description
            created shouldNotBe null
            updated shouldNotBe null
        }
    }
}
