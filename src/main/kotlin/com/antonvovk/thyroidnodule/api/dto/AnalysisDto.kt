package com.antonvovk.thyroidnodule.api.dto

data class AnalysisDto(
    val createdBy: UserDto,
    val updatedBy: UserDto,
    val patientInfo: PatientInfoDto,
    val biopsyAnalysis: BiopsyAnalysisDto,
    val ultrasoundAnalysis: UltrasoundAnalysisDto
)
