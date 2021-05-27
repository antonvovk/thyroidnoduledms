package com.antonvovk.thyroidnodule.api.dto

data class QualificationAnsweredQuestionDto(
    val question: QualificationQuestionDto,
    val givenAnswer: QualificationGivenAnswerDto
)
