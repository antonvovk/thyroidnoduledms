package com.antonvovk.thyroidnodule.db.testing.mappers

import com.antonvovk.thyroidnodule.api.dto.QualificationTestingResultDto
import com.antonvovk.thyroidnodule.common.TwoWayMapper
import com.antonvovk.thyroidnodule.db.testing.models.QualificationTestingResult
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper(componentModel = "spring", uses = [QualificationAnsweredQuestionMapper::class])
interface QualificationTestingResultMapper : TwoWayMapper<QualificationTestingResult, QualificationTestingResultDto> {

    @Mappings(
        Mapping(target = "scoredPercentage", source = "scoredPercentage"),
        Mapping(target = "passed", source = "passed"),
        Mapping(target = "answeredQuestions", source = "answeredQuestions")
    )
    override fun map(from: QualificationTestingResult): QualificationTestingResultDto
}
