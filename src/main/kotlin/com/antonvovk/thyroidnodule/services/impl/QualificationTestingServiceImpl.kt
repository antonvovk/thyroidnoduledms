package com.antonvovk.thyroidnodule.services.impl

import com.antonvovk.thyroidnodule.db.testing.models.AnsweredQuestion
import com.antonvovk.thyroidnodule.db.testing.models.QualificationQuestion
import com.antonvovk.thyroidnodule.db.testing.models.QualificationTestingResult
import com.antonvovk.thyroidnodule.db.testing.repositories.QualificationTestingQuestionRepository
import com.antonvovk.thyroidnodule.db.testing.repositories.QualificationTestingResultRepository
import com.antonvovk.thyroidnodule.services.QualificationTestingService
import org.springframework.stereotype.Service

@Service
class QualificationTestingServiceImpl(
    private val qualificationTestingQuestionRepository: QualificationTestingQuestionRepository,
    private val qualificationTestingResultRepository: QualificationTestingResultRepository
) : QualificationTestingService {

    override fun getAllQuestions(): List<QualificationQuestion> =
        qualificationTestingQuestionRepository.findAll()

    override fun getAllTestingResults(): List<QualificationTestingResult> =
        qualificationTestingResultRepository.findAll()

    override fun testQualification(answeredQuestions: List<AnsweredQuestion>): QualificationTestingResult {
        TODO("Not yet implemented")
    }
}
