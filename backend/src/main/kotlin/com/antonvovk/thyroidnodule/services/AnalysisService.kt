package com.antonvovk.thyroidnodule.services

import com.antonvovk.thyroidnodule.db.analyses.model.Analysis
import com.antonvovk.thyroidnodule.db.analyses.model.UltrasoundImage
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest

interface AnalysisService {

    fun getAll(pageRequest: PageRequest): Page<Analysis>

    fun create(analysis: Analysis): Analysis

    fun update(analysis: Analysis): Analysis

    fun addImage(id: Long, image: UltrasoundImage): Long

    fun removeImage(id: Long, image: UltrasoundImage)
}
