package com.antonvovk.ThyroidNoduleDMS.db.testing.repositories

import com.antonvovk.ThyroidNoduleDMS.db.testing.models.AnsweredQuestion
import com.antonvovk.ThyroidNoduleDMS.db.testing.models.QualificationTestingHistory
import com.antonvovk.ThyroidNoduleDMS.db.testing.models.QuestionAnswer
import com.antonvovk.ThyroidNoduleDMS.db.testing.models.TestingQuestion
import com.antonvovk.ThyroidNoduleDMS.db.users.models.Group
import com.antonvovk.ThyroidNoduleDMS.db.users.models.Permission
import com.antonvovk.ThyroidNoduleDMS.db.users.models.User
import com.antonvovk.ThyroidNoduleDMS.db.users.repositories.GroupRepository
import com.antonvovk.ThyroidNoduleDMS.db.users.repositories.PermissionRepository
import com.antonvovk.ThyroidNoduleDMS.db.users.repositories.UserRepository
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles

@DataJpaTest
@ActiveProfiles("test")
internal class QualificationTestingHistoryRepositoryTest @Autowired constructor(
    private val qualificationTestingHistoryRepository: QualificationTestingHistoryRepository,
    private val testingQuestionRepository: TestingQuestionRepository,
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

        var testingQuestion = TestingQuestion(
            questionText = "Is this true",
            correctAnswer = QuestionAnswer(
                answerText = "True"
            )
        )

        testingQuestion = testingQuestionRepository.save(testingQuestion)

        val qualificationTestingHistory = QualificationTestingHistory(
            user = user,
            scoredPercentage = 0.75f,
            isPassed = true,
            answeredQuestions = listOf(
                AnsweredQuestion(
                    testingQuestion = testingQuestion,
                    givenAnswer = testingQuestion.correctAnswer
                )
            )
        )

        val result = qualificationTestingHistoryRepository.save(qualificationTestingHistory)
        result shouldBe qualificationTestingHistory
    }
}
