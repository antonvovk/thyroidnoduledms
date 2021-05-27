package com.antonvovk.thyroidnodule.db.users.repositories

import com.antonvovk.thyroidnodule.db.users.models.Group
import com.antonvovk.thyroidnodule.db.users.models.Permission
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.shouldBe
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
        result shouldBe group
    }

    @Test
    fun `Test save with full model`() {
        val permissionList = mutableListOf(
            Permission(name = "VIEW_ANALYSIS"),
            Permission(name = "ADD_ANALYSIS")
        )
        permissionRepository.saveAll(permissionList)

        val group = Group(
            name = "Test group",
            description = "Some description about group",
            isDisabled = true,
            permissions = permissionList
        )

        val result = groupRepository.save(group)
        result shouldBe group
    }

    @Test
    fun `Test add and remove permission`() {
        val permissionList = mutableListOf(
            Permission(name = "VIEW_ANALYSIS"),
            Permission(name = "ADD_ANALYSIS")
        )
        permissionRepository.saveAll(permissionList)

        val group = Group(
            name = "Test group",
            description = "Some description about group",
            isDisabled = true,
            permissions = mutableListOf(permissionList[0])
        )

        var result = groupRepository.save(group)
        result shouldBe group

        result.permissions.add(permissionList[1])
        result = groupRepository.save(result)
        assertSoftly(result) {
            permissions shouldBe permissions
        }

        result.permissions.removeAt(0)
        result = groupRepository.save(result)
        assertSoftly(result) {
            permissions shouldBe mutableListOf(permissionList[1])
        }
    }
}
