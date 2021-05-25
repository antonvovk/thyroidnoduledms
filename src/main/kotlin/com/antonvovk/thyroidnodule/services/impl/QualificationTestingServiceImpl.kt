package com.antonvovk.thyroidnodule.services.impl

import com.antonvovk.thyroidnodule.db.testing.models.AnsweredQuestion
import com.antonvovk.thyroidnodule.db.testing.models.QualificationQuestion
import com.antonvovk.thyroidnodule.db.testing.models.QualificationTestingResult
import com.antonvovk.thyroidnodule.db.testing.repositories.QualificationQuestionRepository
import com.antonvovk.thyroidnodule.db.testing.repositories.QualificationTestingResultRepository
import com.antonvovk.thyroidnodule.services.QualificationTestingService
import org.springframework.stereotype.Service

@Service
class QualificationTestingServiceImpl(
    private val qualificationQuestionRepository: QualificationQuestionRepository,
    private val qualificationTestingResultRepository: QualificationTestingResultRepository
) : QualificationTestingService {

    override fun getAllQuestions(): List<QualificationQuestion> =
        qualificationQuestionRepository.findAll()

    override fun getAllTestingResults(): List<QualificationTestingResult> =
        qualificationTestingResultRepository.findAll()

    override fun testQualification(answeredQuestions: List<AnsweredQuestion>): QualificationTestingResult {
        TODO("Not yet implemented")
    }
}
