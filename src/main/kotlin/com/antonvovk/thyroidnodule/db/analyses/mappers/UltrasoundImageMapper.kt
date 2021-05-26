package com.antonvovk.thyroidnodule.db.analyses.mappers

import com.antonvovk.thyroidnodule.api.dto.UltrasoundImageDto
import com.antonvovk.thyroidnodule.common.TwoWayMapper
import com.antonvovk.thyroidnodule.db.analyses.model.UltrasoundImage
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface UltrasoundImageMapper : TwoWayMapper<UltrasoundImage, UltrasoundImageDto>
