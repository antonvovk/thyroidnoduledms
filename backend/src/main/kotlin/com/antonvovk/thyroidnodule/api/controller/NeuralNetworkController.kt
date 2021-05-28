package com.antonvovk.thyroidnodule.api.controller

import com.antonvovk.thyroidnodule.api.dto.AnalysisDto
import com.antonvovk.thyroidnodule.db.analyses.mappers.AnalysisMapper
import com.antonvovk.thyroidnodule.services.NeuralNetworkService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/ai")
@RestController
class NeuralNetworkController(
    private val neuralNetworkService: NeuralNetworkService,
    private val analysisMapper: AnalysisMapper
) {

    @PostMapping
    fun predict(@RequestBody analysis: AnalysisDto): String {
        return neuralNetworkService.predict(analysisMapper.mapReverse(analysis))
    }
}
