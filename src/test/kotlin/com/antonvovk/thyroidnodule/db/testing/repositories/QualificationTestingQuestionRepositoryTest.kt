package com.antonvovk.thyroidnodule.db.testing.repositories

import com.antonvovk.thyroidnodule.db.testing.models.QualificationTestingQuestion
import com.antonvovk.thyroidnodule.db.testing.models.QuestionAnswer
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles

@DataJpaTest
@ActiveProfiles("test")
internal class QualificationTestingQuestionRepositoryTest @Autowired constructor(
    private val testingQuestionRepository: TestingQuestionRepository
) {

    @Test
    fun `Test save with full model`() {
        val testingQuestion = QualificationTestingQuestion(
            questionText = "Is this true",
            correctAnswer = QuestionAnswer(
                answerText = "True"
            )
        )

        val result = testingQuestionRepository.save(testingQuestion)
        result shouldBe testingQuestion
    }
}
