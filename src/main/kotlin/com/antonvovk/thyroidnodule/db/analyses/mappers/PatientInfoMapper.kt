package com.antonvovk.thyroidnodule.db.analyses.mappers

import com.antonvovk.thyroidnodule.api.dto.PatientInfoDto
import com.antonvovk.thyroidnodule.common.TwoWayMapper
import com.antonvovk.thyroidnodule.db.analyses.model.PatientInfo
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface PatientInfoMapper : TwoWayMapper<PatientInfo, PatientInfoDto>
