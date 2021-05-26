package com.antonvovk.thyroidnodule.api.dto

import com.antonvovk.thyroidnodule.db.analyses.model.BethesdaLevel

data class BiopsyAnalysisDto(
    val id: Long,
    val bethesdaLevel: BethesdaLevel
)
