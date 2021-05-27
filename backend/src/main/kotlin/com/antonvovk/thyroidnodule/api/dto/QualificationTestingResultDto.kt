package com.antonvovk.thyroidnodule.api.dto

data class QualificationTestingResultDto(
    val scoredPercentage: Float,
    val passed: Boolean,
    val answeredQuestions: List<QualificationAnsweredQuestionDto>
)
