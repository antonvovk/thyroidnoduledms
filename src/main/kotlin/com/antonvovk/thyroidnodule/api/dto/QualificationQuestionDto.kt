package com.antonvovk.thyroidnodule.api.dto

data class QualificationQuestionDto(
    val id: Long,
    val text: String,
    val answer: QualificationAnswerDto,
    val imageUrl: String
)
