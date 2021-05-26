package com.antonvovk.thyroidnodule.db

import com.antonvovk.thyroidnodule.db.analyses.model.*
import com.antonvovk.thyroidnodule.db.analyses.repositories.AnalysisRepository
import com.antonvovk.thyroidnodule.db.testing.models.QualificationAnswer
import com.antonvovk.thyroidnodule.db.testing.models.QualificationQuestion
import com.antonvovk.thyroidnodule.db.testing.repositories.QualificationQuestionRepository
import com.antonvovk.thyroidnodule.db.users.models.Group
import com.antonvovk.thyroidnodule.db.users.models.Permission
import com.antonvovk.thyroidnodule.db.users.models.User
import com.antonvovk.thyroidnodule.db.users.repositories.GroupRepository
import com.antonvovk.thyroidnodule.db.users.repositories.PermissionRepository
import com.antonvovk.thyroidnodule.db.users.repositories.UserRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class DataLoader(
    private val permissionRepository: PermissionRepository,
    private val groupRepository: GroupRepository,
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val qualificationQuestionRepository: QualificationQuestionRepository,
    private val analysisRepository: AnalysisRepository
) : ApplicationRunner {

    override fun run(args: ApplicationArguments?) {
        val permissionList = mutableListOf(
            Permission(name = "VIEW_ANALYSIS"),
            Permission(name = "ADD_ANALYSIS"),
            Permission(name = "EDIT_ANALYSIS")
        )
        permissionRepository.saveAll(permissionList)

        val groupList = mutableListOf(
            Group(name = "Administrators", permissions = permissionList),
            Group(name = "Specialists", permissions = permissionList)
        )
        groupRepository.saveAll(groupList)

        val user = User(
            firstName = "Admin",
            lastName = "Admin",
            middleName = "Admin",
            workPlace = "ThyroidNoduleDMS",
            email = "admin@admin.com",
            passwordHash = passwordEncoder.encode("password"),
            isQualificationTested = true,
            groups = groupList
        )

        userRepository.save(user)

        val questions = listOf(
            QualificationQuestion(
                questionText = "Чи буває гомогенна структура вузла?",
                correctAnswer = QualificationAnswer(
                    answerText = "Так"
                )
            ),
            QualificationQuestion(
                questionText = "Скільки є рівнів класифікації по системі Bethesda?",
                correctAnswer = QualificationAnswer(
                    answerText = "5"
                )
            ),
            QualificationQuestion(
                questionText = "За ехогенністю вузли поділяються на гіпоехогенні і ... ?",
                correctAnswer = QualificationAnswer(
                    answerText = "ізоехогенні"
                )
            ),
            QualificationQuestion(
                questionText = "Скільки є типів еластограція вузлів?",
                correctAnswer = QualificationAnswer(
                    answerText = "3"
                )
            )
        )
        qualificationQuestionRepository.saveAll(questions)


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

        analysisRepository.save(analysis)
    }
}
