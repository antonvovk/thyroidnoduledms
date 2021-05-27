package com.antonvovk.thyroidnodule.services.impl

import com.antonvovk.thyroidnodule.api.exceptions.common.EntityNotFoundException
import com.antonvovk.thyroidnodule.db.testing.models.AnsweredQuestion
import com.antonvovk.thyroidnodule.db.testing.models.QualificationQuestion
import com.antonvovk.thyroidnodule.db.testing.models.QualificationTestingResult
import com.antonvovk.thyroidnodule.db.testing.repositories.QualificationQuestionRepository
import com.antonvovk.thyroidnodule.db.testing.repositories.QualificationTestingResultRepository
import com.antonvovk.thyroidnodule.db.users.models.User
import com.antonvovk.thyroidnodule.db.users.repositories.UserRepository
import com.antonvovk.thyroidnodule.services.QualificationTestingService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class QualificationTestingServiceImpl(
    private val qualificationQuestionRepository: QualificationQuestionRepository,
    private val qualificationTestingResultRepository: QualificationTestingResultRepository,
    private val userRepository: UserRepository
) : QualificationTestingService {

    override fun getAllQuestions(): List<QualificationQuestion> =
        qualificationQuestionRepository.findAll()

    override fun getAllTestingResults(): List<QualificationTestingResult> =
        qualificationTestingResultRepository.findAll()

    override fun testQualification(answeredQuestions: List<AnsweredQuestion>): QualificationTestingResult {
        val questions = answeredQuestions.map { answeredQuestion ->
            val questionId = answeredQuestion.testingQuestion.id
            val question = qualificationQuestionRepository.findByIdOrNull(questionId)
                ?: throw EntityNotFoundException(questionId, QualificationQuestion::class)
            AnsweredQuestion(question, answeredQuestion.givenAnswer)
        }

        val sum = questions.map { question ->
            if (question.givenAnswer.answerText.lowercase()
                    .contains(question.testingQuestion.correctAnswer.answerText.lowercase())
            ) 1 else 0
        }.sum()

        val percentage = sum.toFloat() / questions.size * 100f
        val isPassed = percentage >= 70f
        val username = SecurityContextHolder.getContext().authentication.principal as String
        val user = userRepository.findByEmail(username)
            ?: throw EntityNotFoundException(username, User::class)

        user.isQualificationTested = isPassed
        userRepository.save(user)

        return qualificationTestingResultRepository.save(
            QualificationTestingResult(
                user, percentage, isPassed, questions
            )
        )
    }
}
