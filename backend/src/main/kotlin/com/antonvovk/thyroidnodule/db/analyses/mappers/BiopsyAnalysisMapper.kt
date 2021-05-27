package com.antonvovk.thyroidnodule.db.analyses.mappers

import com.antonvovk.thyroidnodule.api.dto.BiopsyAnalysisDto
import com.antonvovk.thyroidnodule.common.TwoWayMapper
import com.antonvovk.thyroidnodule.db.analyses.model.BiopsyAnalysis
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface BiopsyAnalysisMapper : TwoWayMapper<BiopsyAnalysis, BiopsyAnalysisDto>
