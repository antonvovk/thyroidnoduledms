package com.antonvovk.thyroidnodule.api.dto

import com.antonvovk.thyroidnodule.db.analyses.model.Sex

data class PatientInfoDto(
    val id: Long,
    val sex: Sex,
    val age: Int
)
