package com.antonvovk.thyroidnodule.db.analyses.repositories

import com.antonvovk.thyroidnodule.db.analyses.model.*
import com.antonvovk.thyroidnodule.db.users.models.Group
import com.antonvovk.thyroidnodule.db.users.models.Permission
import com.antonvovk.thyroidnodule.db.users.models.User
import com.antonvovk.thyroidnodule.db.users.repositories.GroupRepository
import com.antonvovk.thyroidnodule.db.users.repositories.PermissionRepository
import com.antonvovk.thyroidnodule.db.users.repositories.UserRepository
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
            bethesdaLevel = BethesdaLevel.CLASS3
        )
        val ultrasoundAnalysis = UltrasoundAnalysis(
            size = NoduleSize.BETWEEN_ONE_AND_TWO,
            hasConglomerate = true,
            shape = NoduleShape.OVAL,
            contours = NoduleContours.CLEAR_EVEN,
            echogenicity = NoduleEchogenicity.ISOECHOIC,
            vascularization = NoduleVascularization.PERIPHERAL,
            elastography = NoduleElastography.TYPE2,
            autoimmuneThyroiditis = false,
            suspiciousLymphNodes = true,
            thirads = Thirads.CLASS2,
            structure = mutableListOf(NoduleStructure.HETEROGENEOUS),
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
            bethesdaLevel = BethesdaLevel.CLASS4
        )
        val ultrasoundAnalysis = UltrasoundAnalysis(
            size = NoduleSize.LESS_THAN_ONE,
            hasConglomerate = true,
            shape = NoduleShape.IRREGULAR,
            contours = NoduleContours.CLEAR_UNEVEN,
            echogenicity = NoduleEchogenicity.HYPOECHOIC,
            vascularization = NoduleVascularization.CENTRAL,
            elastography = NoduleElastography.TYPE3,
            autoimmuneThyroiditis = false,
            suspiciousLymphNodes = true,
            thirads = Thirads.CLASS4,
            structure = mutableListOf(NoduleStructure.HOMOGENEOUS),
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
