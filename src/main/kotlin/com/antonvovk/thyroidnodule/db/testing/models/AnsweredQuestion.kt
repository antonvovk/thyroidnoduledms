package com.antonvovk.thyroidnodule.db.testing.models

import com.antonvovk.thyroidnodule.db.models.BaseAuditEntity
import javax.persistence.*

@Entity
@Table(name = "AnsweredQuestion", schema = "testing")
data class AnsweredQuestion(

    @ManyToOne
    @JoinColumn(name = "testingQuestionId")
    val testingQuestion: QualificationQuestion,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "givenAnswerId")
    val givenAnswer: QualificationAnswer
) : BaseAuditEntity() {

    @ManyToOne
    @JoinColumn(name = "testingHistoryId")
    lateinit var qualificationTestingHistory: QualificationTestingResult

    @PrePersist
    fun prePersist() {
        givenAnswer.answeredQuestion = this
    }
}
