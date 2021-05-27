package com.antonvovk.thyroidnodule.api.controller

import com.antonvovk.thyroidnodule.api.dto.AnalysisDto
import com.antonvovk.thyroidnodule.db.analyses.mappers.AnalysisMapper
import com.antonvovk.thyroidnodule.security.services.AnalysisService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/analysis")
class AnalysisController(
    private val analysisService: AnalysisService,
    private val analysisMapper: AnalysisMapper
) {

    @GetMapping
    fun getAll(): List<AnalysisDto> {
        val analyses = analysisService.getAll()
        return analysisMapper.map(analyses)
    }
}
