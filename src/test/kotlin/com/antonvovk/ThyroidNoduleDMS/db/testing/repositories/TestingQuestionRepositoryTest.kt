package com.antonvovk.ThyroidNoduleDMS.db.testing.repositories

import com.antonvovk.ThyroidNoduleDMS.db.testing.models.QuestionAnswer
import com.antonvovk.ThyroidNoduleDMS.db.testing.models.TestingQuestion
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles

@DataJpaTest
@ActiveProfiles("test")
internal class TestingQuestionRepositoryTest @Autowired constructor(
    private val testingQuestionRepository: TestingQuestionRepository
) {

    @Test
    fun `Test save with full model`() {
        val testingQuestion = TestingQuestion(
            questionText = "Is this true",
            correctAnswer = QuestionAnswer(
                answerText = "True"
            )
        )

        val result = testingQuestionRepository.save(testingQuestion)
        result shouldBe testingQuestion
    }
}
