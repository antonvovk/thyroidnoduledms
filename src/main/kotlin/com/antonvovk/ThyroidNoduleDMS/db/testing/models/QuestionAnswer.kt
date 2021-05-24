package com.antonvovk.ThyroidNoduleDMS.db.testing.models

import com.antonvovk.ThyroidNoduleDMS.db.models.BaseAuditEntity
import javax.persistence.*

@Entity
@Table(name = "QuestionAnswer", schema = "testing")
data class QuestionAnswer(

    @Column(name = "answerText")
    var answerText: String
) : BaseAuditEntity() {

    @OneToMany(mappedBy = "givenAnswer")
    private val answeredQuestions: List<AnsweredQuestion> = emptyList()

    @OneToOne
    @JoinColumn(name = "testingQuestionId")
    lateinit var testingQuestion: TestingQuestion
}
