package com.antonvovk.thyroidnodule.db.testing.mappers

import com.antonvovk.thyroidnodule.api.dto.response.QualificationQuestionDto
import com.antonvovk.thyroidnodule.common.OneWayMapper
import com.antonvovk.thyroidnodule.db.testing.models.QualificationTestingQuestion
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper(componentModel = "spring", uses = [QualificationAnswerMapper::class])
interface QualificationQuestionMapper : OneWayMapper<QualificationTestingQuestion, QualificationQuestionDto> {

    @Mappings(
        Mapping(target = "text", source = "questionText"),
        Mapping(target = "answer", source = "correctAnswer"),
        Mapping(target = "imageUrl", source = "ultrasoundImage.filename")
    )
    override fun map(from: QualificationTestingQuestion): QualificationQuestionDto
}
