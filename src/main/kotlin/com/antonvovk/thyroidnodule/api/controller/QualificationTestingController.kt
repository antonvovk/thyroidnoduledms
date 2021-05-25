package com.antonvovk.thyroidnodule.api.controller

import com.antonvovk.thyroidnodule.api.dto.response.QualificationQuestionDto
import com.antonvovk.thyroidnodule.db.testing.mappers.QualificationQuestionMapper
import com.antonvovk.thyroidnodule.services.QualificationTestingService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/qualification")
class QualificationTestingController(
    private val qualificationTestingService: QualificationTestingService,
    private val qualificationQuestionMapper: QualificationQuestionMapper
) {

    @GetMapping
    fun getAll(): List<QualificationQuestionDto> {
        val questions = qualificationTestingService.getAllQuestions()
        return qualificationQuestionMapper.map(questions)
    }
}
