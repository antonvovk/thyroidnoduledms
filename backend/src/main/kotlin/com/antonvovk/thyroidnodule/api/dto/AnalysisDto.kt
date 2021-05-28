package com.antonvovk.thyroidnodule.api.dto

import java.time.LocalDateTime

data class AnalysisDto(
    val id: Long,
    val createdBy: UserDto,
    val updatedBy: UserDto,
    val patientInfo: PatientInfoDto,
    val biopsyAnalysis: BiopsyAnalysisDto,
    val ultrasoundAnalysis: UltrasoundAnalysisDto,
    val created: LocalDateTime,
    val updated: LocalDateTime
)
