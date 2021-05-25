package com.antonvovk.thyroidnodule.db.testing.repositories

import com.antonvovk.thyroidnodule.db.testing.models.QualificationAnswer
import com.antonvovk.thyroidnodule.db.testing.models.QualificationQuestion
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles

@DataJpaTest
@ActiveProfiles("test")
internal class QualificationQualificationQuestionRepositoryTest @Autowired constructor(
    private val qualificationQuestionRepository: QualificationQuestionRepository
) {

    @Test
    fun `Test save with full model`() {
        val testingQuestion = QualificationQuestion(
            questionText = "Is this true",
            correctAnswer = QualificationAnswer(
                answerText = "True"
            )
        )

        val result = qualificationQuestionRepository.save(testingQuestion)
        result shouldBe testingQuestion
    }
}
