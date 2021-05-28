package com.antonvovk.thyroidnodule.services

import com.antonvovk.thyroidnodule.db.analyses.model.Analysis
import com.antonvovk.thyroidnodule.db.analyses.model.UltrasoundImage

interface AnalysisService {

    fun getAll(): List<Analysis>

    fun create(analysis: Analysis)

    fun update(analysis: Analysis)

    fun addImage(id: Long, image: UltrasoundImage): Long

    fun removeImage(id: Long, image: UltrasoundImage)
}
