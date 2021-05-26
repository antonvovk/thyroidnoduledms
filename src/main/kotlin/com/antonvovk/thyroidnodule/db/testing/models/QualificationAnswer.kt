package com.antonvovk.thyroidnodule.db.testing.models

import com.antonvovk.thyroidnodule.db.models.BaseAuditEntity
import javax.persistence.*

@Entity
@Table(name = "QuestionAnswer", schema = "testing")
data class QualificationAnswer(

    @Column(name = "answerText")
    var answerText: String
) : BaseAuditEntity() {

    @OneToOne(mappedBy = "givenAnswer")
    lateinit var answeredQuestion: AnsweredQuestion

    @OneToOne
    @JoinColumn(name = "testingQuestionId")
    lateinit var testingQuestion: QualificationQuestion
}
