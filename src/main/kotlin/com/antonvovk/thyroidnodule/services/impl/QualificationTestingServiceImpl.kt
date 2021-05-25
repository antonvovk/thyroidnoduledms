package com.antonvovk.thyroidnodule.services.impl

import com.antonvovk.thyroidnodule.db.testing.models.AnsweredQuestion
import com.antonvovk.thyroidnodule.db.testing.models.QualificationQuestion
import com.antonvovk.thyroidnodule.db.testing.models.QualificationTestingResult
import com.antonvovk.thyroidnodule.db.testing.repositories.QualificationTestingQuestionRepository
import com.antonvovk.thyroidnodule.services.QualificationTestingService
import org.springframework.stereotype.Service

@Service
class QualificationTestingServiceImpl(
    private val qualificationTestingQuestionRepository: QualificationTestingQuestionRepository
) : QualificationTestingService {

    override fun getAllQuestions(): List<QualificationQuestion> =
        qualificationTestingQuestionRepository.findAll()

    override fun testQualification(answeredQuestions: List<AnsweredQuestion>): QualificationTestingResult {
        TODO("Not yet implemented")
    }
}
