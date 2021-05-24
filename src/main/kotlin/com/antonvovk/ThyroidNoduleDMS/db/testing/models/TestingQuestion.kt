package com.antonvovk.ThyroidNoduleDMS.db.testing.models

import com.antonvovk.ThyroidNoduleDMS.db.analyses.model.UltrasoundImage
import com.antonvovk.ThyroidNoduleDMS.db.models.BaseAuditEntity
import javax.persistence.*

@Entity
@Table(name = "TestingQuestion", schema = "testing")
data class TestingQuestion(

    @Column(name = "questionText")
    var questionText: String,

    @OneToOne(mappedBy = "testingQuestion")
    val correctAnswer: QuestionAnswer,

    @OneToOne
    @JoinColumn(name = "ultrasoundImageId")
    var ultrasoundImage: UltrasoundImage
) : BaseAuditEntity() {

    @PrePersist
    fun prePersist() {
        correctAnswer.testingQuestion = this
    }
}
