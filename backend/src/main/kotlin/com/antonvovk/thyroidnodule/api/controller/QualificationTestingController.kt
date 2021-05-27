package com.antonvovk.thyroidnodule.api.controller

import com.antonvovk.thyroidnodule.api.dto.QualificationAnsweredQuestionDto
import com.antonvovk.thyroidnodule.api.dto.QualificationQuestionDto
import com.antonvovk.thyroidnodule.api.dto.QualificationTestingResultDto
import com.antonvovk.thyroidnodule.db.testing.mappers.QualificationAnsweredQuestionMapper
import com.antonvovk.thyroidnodule.db.testing.mappers.QualificationQuestionMapper
import com.antonvovk.thyroidnodule.db.testing.mappers.QualificationTestingResultMapper
import com.antonvovk.thyroidnodule.services.QualificationTestingService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/qualification")
class QualificationTestingController(
    private val qualificationTestingService: QualificationTestingService,
    private val qualificationQuestionMapper: QualificationQuestionMapper,
    private val qualificationAnsweredQuestionMapper: QualificationAnsweredQuestionMapper,
    private val qualificationTestingResultMapper: QualificationTestingResultMapper
) {

    @GetMapping
    fun getAll(): List<QualificationQuestionDto> {
        val questions = qualificationTestingService.getAllQuestions()
        return qualificationQuestionMapper.map(questions)
    }

    @GetMapping("/result")
    fun getAllTestingResults(): List<QualificationTestingResultDto> {
        val qualificationTestingResult = qualificationTestingService.getAllTestingResults()
        return qualificationTestingResultMapper.map(qualificationTestingResult)
    }

    @PostMapping
    fun testQualification(@RequestBody body: List<QualificationAnsweredQuestionDto>): QualificationTestingResultDto {
        val answeredQuestions = qualificationAnsweredQuestionMapper.mapReverse(body)
        val qualificationTestingResult = qualificationTestingService.testQualification(answeredQuestions)
        return qualificationTestingResultMapper.map(qualificationTestingResult)
    }
}
