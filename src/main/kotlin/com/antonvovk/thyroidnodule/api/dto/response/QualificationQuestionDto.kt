package com.antonvovk.thyroidnodule.api.dto.response

data class QualificationQuestionDto(
    val text: String,
    val answer: QualificationAnswerDto,
    val imageUrl: String
)
