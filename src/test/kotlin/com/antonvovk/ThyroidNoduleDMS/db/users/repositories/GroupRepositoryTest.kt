package com.antonvovk.ThyroidNoduleDMS.db.users.repositories

import com.antonvovk.ThyroidNoduleDMS.db.users.models.Group
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
internal class GroupRepositoryTest @Autowired constructor(
    private val groupRepository: GroupRepository,
    private val permissionRepository: PermissionRepository
) {

    @Test
    fun `Test save with minimal model`() {
        val group = Group(
            name = "Test group"
        )

        val result = groupRepository.save(group)
        assertSoftly(result) {
            id shouldNotBe null
            name shouldBe group.name
            description shouldBe null
            isDisabled shouldBe false
            permissions shouldBe mutableListOf()
            created shouldNotBe null
            updated shouldNotBe null
        }
    }

    @Test
    fun `Test save with full model`() {
        val permissions = mutableListOf(
            Permission(name = "VIEW_ANALYSIS"),
            Permission(name = "ADD_ANALYSIS")
        )
        permissionRepository.saveAll(permissions)

        val group = Group(
            name = "Test group",
            description = "Some description about group",
            isDisabled = true,
            permissions = permissions
        )

        val result = groupRepository.save(group)
        assertSoftly(result) {
            id shouldNotBe null
            name shouldBe group.name
            description shouldBe group.description
            isDisabled shouldBe group.isDisabled
            permissions shouldBe group.permissions
            created shouldNotBe null
            updated shouldNotBe null
        }
    }

    @Test
    fun `Test add and remove permission`() {
        val permissions = mutableListOf(
            Permission(name = "VIEW_ANALYSIS"),
            Permission(name = "ADD_ANALYSIS")
        )
        permissionRepository.saveAll(permissions)

        val group = Group(
            name = "Test group",
            description = "Some description about group",
            isDisabled = true,
            permissions = mutableListOf(permissions[0])
        )

        var result = groupRepository.save(group)
        assertSoftly(result) {
            id shouldNotBe null
            name shouldBe group.name
            description shouldBe group.description
            isDisabled shouldBe group.isDisabled
            permissions shouldBe group.permissions
            created shouldNotBe null
            updated shouldNotBe null
        }

        result.permissions.add(permissions[1])
        result = groupRepository.save(result)
        assertSoftly(result) {
            permissions shouldBe permissions
        }

        result.permissions.removeAt(0)
        result = groupRepository.save(result)
        assertSoftly(result) {
            permissions shouldBe mutableListOf(permissions[1])
        }
    }
}
