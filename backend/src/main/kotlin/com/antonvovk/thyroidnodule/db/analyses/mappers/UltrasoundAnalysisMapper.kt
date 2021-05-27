package com.antonvovk.thyroidnodule.db.analyses.mappers

import com.antonvovk.thyroidnodule.api.dto.UltrasoundAnalysisDto
import com.antonvovk.thyroidnodule.common.TwoWayMapper
import com.antonvovk.thyroidnodule.db.analyses.model.UltrasoundAnalysis
import org.mapstruct.Mapper

@Mapper(componentModel = "spring", uses = [UltrasoundImageMapper::class])
interface UltrasoundAnalysisMapper : TwoWayMapper<UltrasoundAnalysis, UltrasoundAnalysisDto>
