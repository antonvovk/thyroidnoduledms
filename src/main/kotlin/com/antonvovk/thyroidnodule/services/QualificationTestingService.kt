package com.antonvovk.thyroidnodule.services

import com.antonvovk.thyroidnodule.db.testing.models.QualificationQuestion

interface QualificationTestingService {

    fun getAllQuestions(): List<QualificationQuestion>
}
