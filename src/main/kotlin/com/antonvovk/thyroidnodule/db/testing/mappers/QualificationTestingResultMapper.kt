package com.antonvovk.thyroidnodule.db.testing.mappers

import com.antonvovk.thyroidnodule.api.dto.QualificationTestingResultDto
import com.antonvovk.thyroidnodule.common.TwoWayMapper
import com.antonvovk.thyroidnodule.db.testing.models.QualificationTestingResult
import org.mapstruct.Mapper

@Mapper(componentModel = "spring", uses = [QualificationAnsweredQuestionMapper::class])
interface QualificationTestingResultMapper : TwoWayMapper<QualificationTestingResult, QualificationTestingResultDto>
