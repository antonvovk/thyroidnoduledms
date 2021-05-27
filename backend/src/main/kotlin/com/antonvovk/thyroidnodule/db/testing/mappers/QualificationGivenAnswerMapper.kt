package com.antonvovk.thyroidnodule.db.testing.mappers

import com.antonvovk.thyroidnodule.api.dto.QualificationGivenAnswerDto
import com.antonvovk.thyroidnodule.common.TwoWayMapper
import com.antonvovk.thyroidnodule.db.testing.models.QualificationAnswer
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper(componentModel = "spring")
interface QualificationGivenAnswerMapper : TwoWayMapper<QualificationAnswer, QualificationGivenAnswerDto> {

    @Mappings(
        Mapping(target = "text", source = "answerText")
    )
    override fun map(from: QualificationAnswer): QualificationGivenAnswerDto
}
