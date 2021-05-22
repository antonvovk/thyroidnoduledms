package com.antonvovk.ThyroidNoduleDMS.db.users.repositories

import com.antonvovk.ThyroidNoduleDMS.db.users.models.Group
import com.antonvovk.ThyroidNoduleDMS.db.users.models.Permission
import com.antonvovk.ThyroidNoduleDMS.db.users.models.User
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles

@DataJpaTest
@ActiveProfiles("test")
internal class UserRepositoryTest @Autowired constructor(
    private val userRepository: UserRepository,
    private val groupRepository: GroupRepository,
    private val permissionRepository: PermissionRepository
) {

    @Test
    fun `Test save with minimal model`() {
        val user = User(
            firstName = "Anton",
            lastName = "Vovk",
            middleName = "Vasylyovych",
            workPlace = "VIG IT",
            email = "archwolf@protonmail.com",
            passwordHash = "as8gu23asdhkbasd98h1"
        )

        val result = userRepository.save(user)
        assertSoftly(result) {
            id shouldNotBe null
            firstName shouldBe user.firstName
            lastName shouldBe user.lastName
            middleName shouldBe user.middleName
            workPlace shouldBe user.workPlace
            email shouldBe user.email
            passwordHash shouldBe user.passwordHash
            isQualificationTested shouldBe false
            groups shouldBe mutableListOf()
            created shouldNotBe null
            updated shouldNotBe null
        }
    }

    @Test
    fun `Test save with full model`() {
        val permissionList = mutableListOf(
            Permission(name = "VIEW_ANALYSIS"),
            Permission(name = "ADD_ANALYSIS"),
            Permission(name = "EDIT_ANALYSIS")
        )
        permissionRepository.saveAll(permissionList)

        val groupList = mutableListOf(
            Group(name = "Group1", permissions = mutableListOf(permissionList[0], permissionList[1])),
            Group(name = "Group2", permissions = mutableListOf(permissionList[0], permissionList[2]))
        )
        groupRepository.saveAll(groupList)

        val user = User(
            firstName = "Anton",
            lastName = "Vovk",
            middleName = "Vasylyovych",
            workPlace = "VIG IT",
            email = "archwolf@protonmail.com",
            passwordHash = "as8gu23asdhkbasd98h1",
            isQualificationTested = true,
            groups = groupList
        )

        val result = userRepository.save(user)
        assertSoftly(result) {
            id shouldNotBe null
            firstName shouldBe user.firstName
            lastName shouldBe user.lastName
            middleName shouldBe user.middleName
            workPlace shouldBe user.workPlace
            email shouldBe user.email
            passwordHash shouldBe user.passwordHash
            isQualificationTested shouldBe user.isQualificationTested
            groups shouldBe user.groups
            created shouldNotBe null
            updated shouldNotBe null
        }
    }

    @Test
    fun `Test add and remove groups`() {
        val permissionList = mutableListOf(
            Permission(name = "VIEW_ANALYSIS"),
            Permission(name = "ADD_ANALYSIS"),
            Permission(name = "EDIT_ANALYSIS")
        )
        permissionRepository.saveAll(permissionList)

        val groupList = mutableListOf(
            Group(name = "Group1", permissions = mutableListOf(permissionList[0], permissionList[1])),
            Group(name = "Group2", permissions = mutableListOf(permissionList[0], permissionList[2]))
        )
        groupRepository.saveAll(groupList)

        val user = User(
            firstName = "Anton",
            lastName = "Vovk",
            middleName = "Vasylyovych",
            workPlace = "VIG IT",
            email = "archwolf@protonmail.com",
            passwordHash = "as8gu23asdhkbasd98h1",
            isQualificationTested = true,
            groups = mutableListOf(groupList[0])
        )

        var result = userRepository.save(user)
        assertSoftly(result) {
            id shouldNotBe null
            firstName shouldBe user.firstName
            lastName shouldBe user.lastName
            middleName shouldBe user.middleName
            workPlace shouldBe user.workPlace
            email shouldBe user.email
            passwordHash shouldBe user.passwordHash
            isQualificationTested shouldBe user.isQualificationTested
            groups shouldBe user.groups
            created shouldNotBe null
            updated shouldNotBe null
        }

        result.groups.add(groupList[1])
        result = userRepository.save(result)
        assertSoftly(result) {
            groups shouldBe groups
        }

        result.groups.removeAt(0)
        result = userRepository.save(result)
        assertSoftly(result) {
            groups shouldBe mutableListOf(groupList[1])
        }
    }
}
