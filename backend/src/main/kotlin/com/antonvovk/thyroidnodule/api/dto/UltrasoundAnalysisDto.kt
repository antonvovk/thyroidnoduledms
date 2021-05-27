package com.antonvovk.thyroidnodule.api.dto

import com.antonvovk.thyroidnodule.db.analyses.model.*

data class UltrasoundAnalysisDto(

    var size: NoduleSize,
    var hasConglomerate: Boolean,
    var shape: NoduleShape,
    var contours: NoduleContours,
    var echogenicity: NoduleEchogenicity,
    var vascularization: NoduleVascularization,
    var elastography: NoduleElastography,
    var autoimmuneThyroiditis: Boolean,
    var suspiciousLymphNodes: Boolean,
    var thirads: Thirads,
    val structure: MutableList<NoduleStructure> = mutableListOf(),
    val images: MutableList<UltrasoundImageDto> = mutableListOf()
)
