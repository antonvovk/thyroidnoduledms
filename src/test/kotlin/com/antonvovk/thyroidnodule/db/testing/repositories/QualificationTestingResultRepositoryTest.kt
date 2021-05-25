package com.antonvovk.thyroidnodule.db.testing.repositories

import com.antonvovk.thyroidnodule.db.testing.models.AnsweredQuestion
import com.antonvovk.thyroidnodule.db.testing.models.QualificationAnswer
import com.antonvovk.thyroidnodule.db.testing.models.QualificationQuestion
import com.antonvovk.thyroidnodule.db.testing.models.QualificationTestingResult
import com.antonvovk.thyroidnodule.db.users.models.Group
import com.antonvovk.thyroidnodule.db.users.models.Permission
import com.antonvovk.thyroidnodule.db.users.models.User
import com.antonvovk.thyroidnodule.db.users.repositories.GroupRepository
import com.antonvovk.thyroidnodule.db.users.repositories.PermissionRepository
import com.antonvovk.thyroidnodule.db.users.repositories.UserRepository
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles

@DataJpaTest
@ActiveProfiles("test")
internal class QualificationTestingResultRepositoryTest @Autowired constructor(
    private val qualificationTestingHistoryRepository: QualificationTestingHistoryRepository,
    private val qualificationTestingQuestionRepository: QualificationTestingQuestionRepository,
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

        var testingQuestion = QualificationQuestion(
            questionText = "Is this true",
            correctAnswer = QualificationAnswer(
                answerText = "True"
            )
        )

        testingQuestion = qualificationTestingQuestionRepository.save(testingQuestion)

        val qualificationTestingHistory = QualificationTestingResult(
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
