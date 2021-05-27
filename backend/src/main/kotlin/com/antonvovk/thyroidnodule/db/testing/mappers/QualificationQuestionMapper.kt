package com.antonvovk.thyroidnodule.db.testing.mappers

import com.antonvovk.thyroidnodule.api.dto.QualificationQuestionDto
import com.antonvovk.thyroidnodule.common.TwoWayMapper
import com.antonvovk.thyroidnodule.db.testing.models.QualificationQuestion
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper(componentModel = "spring", uses = [QualificationAnswerMapper::class])
interface QualificationQuestionMapper : TwoWayMapper<QualificationQuestion, QualificationQuestionDto> {

    @Mappings(
        Mapping(target = "text", source = "questionText"),
        Mapping(target = "answer", source = "correctAnswer"),
        Mapping(target = "imageUrl", source = "ultrasoundImage.filename")
    )
    override fun map(from: QualificationQuestion): QualificationQuestionDto

    @Mappings(
        Mapping(target = "id", source = "id"),
        Mapping(target = "questionText", source = "text"),
        Mapping(target = "correctAnswer", source = "answer"),
    )
    override fun mapReverse(from: QualificationQuestionDto): QualificationQuestion
}
