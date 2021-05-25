package com.antonvovk.thyroidnodule.db.testing.models

import com.antonvovk.thyroidnodule.db.models.BaseAuditEntity
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "AnsweredQuestion", schema = "testing")
data class AnsweredQuestion(

    @ManyToOne
    @JoinColumn(name = "testingQuestionId")
    val testingQuestion: QualificationTestingQuestion,

    @ManyToOne
    @JoinColumn(name = "givenAnswerId")
    val givenAnswer: QuestionAnswer
) : BaseAuditEntity() {

    @ManyToOne
    @JoinColumn(name = "testingHistoryId")
    lateinit var qualificationTestingHistory: QualificationTestingHistory
}
