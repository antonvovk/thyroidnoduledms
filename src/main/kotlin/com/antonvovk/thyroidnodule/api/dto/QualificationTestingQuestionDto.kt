package com.antonvovk.thyroidnodule.api.dto

data class QualificationTestingQuestionDto(
    val text: String,
    val answer: QualificationQuestionAnswerDto,
    val imageUrl: String
) {

    data class QualificationQuestionAnswerDto(
        val text: String
    )
}
