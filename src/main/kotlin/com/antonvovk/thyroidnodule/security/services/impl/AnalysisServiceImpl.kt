package com.antonvovk.thyroidnodule.security.services.impl

import com.antonvovk.thyroidnodule.db.analyses.model.Analysis
import com.antonvovk.thyroidnodule.db.analyses.repositories.AnalysisRepository
import com.antonvovk.thyroidnodule.security.services.AnalysisService
import org.springframework.stereotype.Service

@Service
class AnalysisServiceImpl(
    private val analysisRepository: AnalysisRepository
) : AnalysisService {

    override fun getAll(): List<Analysis> = analysisRepository.findAll()
}
