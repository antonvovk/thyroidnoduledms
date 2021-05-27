package com.antonvovk.thyroidnodule.db.testing.mappers

import com.antonvovk.thyroidnodule.api.dto.QualificationAnsweredQuestionDto
import com.antonvovk.thyroidnodule.common.TwoWayMapper
import com.antonvovk.thyroidnodule.db.testing.models.AnsweredQuestion
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper(componentModel = "spring", uses = [QualificationQuestionMapper::class, QualificationGivenAnswerMapper::class])
interface QualificationAnsweredQuestionMapper : TwoWayMapper<AnsweredQuestion, QualificationAnsweredQuestionDto> {

    @Mappings(
        Mapping(target = "question", source = "testingQuestion"),
        Mapping(target = "givenAnswer", source = "givenAnswer")
    )
    override fun map(from: AnsweredQuestion): QualificationAnsweredQuestionDto
}
