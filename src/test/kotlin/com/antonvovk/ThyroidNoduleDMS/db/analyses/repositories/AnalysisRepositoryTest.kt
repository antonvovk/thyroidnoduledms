package com.antonvovk.ThyroidNoduleDMS.db.analyses.repositories

import com.antonvovk.ThyroidNoduleDMS.db.analyses.model.*
import com.antonvovk.ThyroidNoduleDMS.db.users.models.Group
import com.antonvovk.ThyroidNoduleDMS.db.users.models.Permission
import com.antonvovk.ThyroidNoduleDMS.db.users.models.User
import com.antonvovk.ThyroidNoduleDMS.db.users.repositories.GroupRepository
import com.antonvovk.ThyroidNoduleDMS.db.users.repositories.PermissionRepository
import com.antonvovk.ThyroidNoduleDMS.db.users.repositories.UserRepository
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles

@DataJpaTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class AnalysisRepositoryTest @Autowired constructor(
    private val analysisRepository: AnalysisRepository,
    private val userRepository: UserRepository,
    private val groupRepository: GroupRepository,
    private val permissionRepository: PermissionRepository
) {

    private var permissionList = mutableListOf(
        Permission(name = "VIEW_ANALYSIS"),
        Permission(name = "ADD_ANALYSIS"),
        Permission(name = "EDIT_ANALYSIS")
    )
    private var groupList = mutableListOf(
        Group(name = "Group1"),
        Group(name = "Group2")
    )
    private var user = User(
        firstName = "Anton",
        lastName = "Vovk",
        middleName = "Vasylyovych",
        workPlace = "VIG IT",
        email = "archwolf@protonmail.com",
        passwordHash = "as8gu23asdhkbasd98h1",
        isQualificationTested = true
    )

    @Test
    fun `Test save with minimal model`() {
        val permissions = permissionRepository.saveAll(permissionList.toMutableList())
        groupList.forEach {
            it.permissions.apply {
                clear()
                addAll(permissions)
            }
        }
        val groups = groupRepository.saveAll(groupList)
        val usr = user.copy(groups = groups)
        user = userRepository.save(usr)

        val patientInfo = PatientInfo(
            sex = Sex.F,
            age = 22
        )
        val biopsyAnalysis = BiopsyAnalysis(
            bethesdaLevel = BethesdaLevel.Class2
        )
        val ultrasoundAnalysis = UltrasoundAnalysis(
            size = NoduleSize.AA,
            hasConglomerate = true,
            shape = NoduleShape.AA,
            contours = NoduleContours.AA,
            echogenicity = NoduleEchogenicity.AA,
            vascularization = NoduleVascularization.AA,
            elastography = NoduleElastography.AA,
            autoimmuneThyroiditis = false,
            suspiciousLymphNodes = true,
            thirads = Thirads.AA,
            structure = mutableListOf(NoduleStructure.AA),
            images = mutableListOf(
                UltrasoundImage(
                    filename = "filename",
                    height = 320,
                    width = 480
                )
            )
        )
        val analysis = Analysis(
            createdBy = user,
            updatedBy = user,
            patientInfo = patientInfo,
            biopsyAnalysis = biopsyAnalysis,
            ultrasoundAnalysis = ultrasoundAnalysis,
        )

        val result = analysisRepository.save(analysis)
        result shouldBe analysis
    }

    @Test
    fun `Test save with full model`() {
        val permissions = permissionRepository.saveAll(permissionList.toMutableList())
        groupList.forEach {
            it.permissions.apply {
                clear()
                addAll(permissions)
            }
        }
        val groups = groupRepository.saveAll(groupList)
        val usr = user.copy(groups = groups)
        user = userRepository.save(usr)

        val patientInfo = PatientInfo(
            sex = Sex.F,
            age = 22
        )
        val biopsyAnalysis = BiopsyAnalysis(
            bethesdaLevel = BethesdaLevel.Class2
        )
        val ultrasoundAnalysis = UltrasoundAnalysis(
            size = NoduleSize.AA,
            hasConglomerate = true,
            shape = NoduleShape.AA,
            contours = NoduleContours.AA,
            echogenicity = NoduleEchogenicity.AA,
            vascularization = NoduleVascularization.AA,
            elastography = NoduleElastography.AA,
            autoimmuneThyroiditis = false,
            suspiciousLymphNodes = true,
            thirads = Thirads.AA,
            structure = mutableListOf(NoduleStructure.AA),
            images = mutableListOf(
                UltrasoundImage(
                    filename = "filename",
                    height = 320,
                    width = 480
                )
            )
        )
        val analysis = Analysis(
            createdBy = user,
            updatedBy = user,
            patientInfo = patientInfo,
            biopsyAnalysis = biopsyAnalysis,
            ultrasoundAnalysis = ultrasoundAnalysis,
        )

        val result = analysisRepository.save(analysis)
        result shouldBe analysis
    }
}
