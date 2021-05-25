package com.antonvovk.thyroidnodule.db.testing.mappers

import com.antonvovk.thyroidnodule.api.dto.response.QualificationAnswerDto
import com.antonvovk.thyroidnodule.common.OneWayMapper
import com.antonvovk.thyroidnodule.db.testing.models.QualificationAnswer
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper(componentModel = "spring")
interface QualificationAnswerMapper : OneWayMapper<QualificationAnswer, QualificationAnswerDto> {

    @Mappings(
        Mapping(target = "text", source = "answerText")
    )
    override fun map(from: QualificationAnswer): QualificationAnswerDto
}
