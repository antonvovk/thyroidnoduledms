package com.antonvovk.thyroidnodule.services.impl

import com.antonvovk.thyroidnodule.db.testing.models.QualificationTestingQuestion
import com.antonvovk.thyroidnodule.services.QualificationTestingService
import org.springframework.stereotype.Service

@Service
class QualificationTestingServiceImpl : QualificationTestingService {

    override fun getAllQuestions(): List<QualificationTestingQuestion> {
        TODO("Not yet implemented")
    }
}
