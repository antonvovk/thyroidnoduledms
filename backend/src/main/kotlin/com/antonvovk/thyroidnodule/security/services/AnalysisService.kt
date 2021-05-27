package com.antonvovk.thyroidnodule.security.services

import com.antonvovk.thyroidnodule.db.analyses.model.Analysis

interface AnalysisService {

    fun getAll(): List<Analysis>

    fun create(analysis: Analysis)

    fun update(analysis: Analysis)
}
