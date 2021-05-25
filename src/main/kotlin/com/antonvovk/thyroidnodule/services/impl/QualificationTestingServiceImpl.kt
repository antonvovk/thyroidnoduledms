package com.antonvovk.thyroidnodule.services.impl

import com.antonvovk.thyroidnodule.db.testing.models.QualificationTestingQuestion
import com.antonvovk.thyroidnodule.db.testing.repositories.QualificationTestingQuestionRepository
import com.antonvovk.thyroidnodule.services.QualificationTestingService
import org.springframework.stereotype.Service

@Service
class QualificationTestingServiceImpl(
    private val qualificationTestingQuestionRepository: QualificationTestingQuestionRepository
) : QualificationTestingService {

    override fun getAllQuestions(): List<QualificationTestingQuestion> =
        qualificationTestingQuestionRepository.findAll()
}
