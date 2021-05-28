package com.antonvovk.thyroidnodule.api.controller

import com.antonvovk.thyroidnodule.api.dto.AnalysisDto
import com.antonvovk.thyroidnodule.api.dto.PageDto
import com.antonvovk.thyroidnodule.api.dto.UltrasoundImageDto
import com.antonvovk.thyroidnodule.db.analyses.mappers.AnalysisMapper
import com.antonvovk.thyroidnodule.db.analyses.mappers.UltrasoundImageMapper
import com.antonvovk.thyroidnodule.services.AnalysisService
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/analysis")
class AnalysisController(
    private val analysisService: AnalysisService,
    private val analysisMapper: AnalysisMapper,
    private val ultrasoundImageMapper: UltrasoundImageMapper
) {

    @GetMapping
    fun getAll(@RequestParam page: Int, @RequestParam size: Int): PageDto<AnalysisDto> {
        val pageRequest = PageRequest.of(page, size)
        val analyses = analysisService.getAll(pageRequest)
        return PageDto(
            analysisMapper.map(analyses.toList()),
            analyses.totalElements
        )
    }

    @PostMapping
    fun create(@RequestBody body: AnalysisDto): AnalysisDto {
        val analysis = analysisMapper.mapReverse(body)
        return analysisMapper.map(analysisService.create(analysis))
    }

    @PutMapping
    fun update(@RequestBody body: AnalysisDto): AnalysisDto {
        val analysis = analysisMapper.mapReverse(body)
        return analysisMapper.map(analysisService.update(analysis))
    }

    @PostMapping("/{id}/image")
    fun addImage(@PathVariable id: Long, @RequestBody body: UltrasoundImageDto): Long {
        val image = ultrasoundImageMapper.mapReverse(body)
        return analysisService.addImage(id, image)
    }

    @PutMapping("/{id}/image")
    fun deleteImage(@PathVariable id: Long, @RequestBody body: UltrasoundImageDto) {
        val image = ultrasoundImageMapper.mapReverse(body)
        analysisService.removeImage(id, image)
    }
}
