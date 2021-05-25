package com.antonvovk.thyroidnodule.api.dto

data class QualificationTestingResultDto(
    val scoredPercentage: Float,
    val isPassed: Boolean,
    val answeredQuestions: List<QualificationAnsweredQuestionDto>
)
