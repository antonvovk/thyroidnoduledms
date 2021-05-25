package com.antonvovk.thyroidnodule.services

import com.antonvovk.thyroidnodule.db.testing.models.QualificationTestingQuestion

interface QualificationTestingService {

    fun getAllQuestions(): List<QualificationTestingQuestion>
}
