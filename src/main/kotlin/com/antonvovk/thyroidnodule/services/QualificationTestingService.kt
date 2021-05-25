package com.antonvovk.thyroidnodule.services

import com.antonvovk.thyroidnodule.db.testing.models.AnsweredQuestion
import com.antonvovk.thyroidnodule.db.testing.models.QualificationQuestion
import com.antonvovk.thyroidnodule.db.testing.models.QualificationTestingResult

interface QualificationTestingService {

    fun getAllQuestions(): List<QualificationQuestion>

    fun testQualification(answeredQuestions: List<AnsweredQuestion>): QualificationTestingResult
}
