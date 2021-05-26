package com.antonvovk.thyroidnodule.db.analyses.mappers

import com.antonvovk.thyroidnodule.api.dto.AnalysisDto
import com.antonvovk.thyroidnodule.common.TwoWayMapper
import com.antonvovk.thyroidnodule.db.analyses.model.Analysis
import com.antonvovk.thyroidnodule.db.users.mappers.UserMapper
import org.mapstruct.Mapper

@Mapper(
    componentModel = "spring",
    uses = [UserMapper::class, PatientInfoMapper::class, BiopsyAnalysisMapper::class, UltrasoundAnalysisMapper::class]
)
interface AnalysisMapper : TwoWayMapper<Analysis, AnalysisDto>
