package com.antonvovk.ThyroidNoduleDMS.db.testing.models

import com.antonvovk.ThyroidNoduleDMS.db.analyses.model.UltrasoundImage
import com.antonvovk.ThyroidNoduleDMS.db.models.BaseAuditEntity
import javax.persistence.*

@Entity
@Table(name = "TestingQuestion", schema = "testing")
data class TestingQuestion(

    @Column(name = "questionText")
    var questionText: String,

    @OneToOne(mappedBy = "testingQuestion", cascade = [CascadeType.ALL])
    val correctAnswer: QuestionAnswer,

    @OneToOne
    @JoinColumn(name = "ultrasoundImageId")
    var ultrasoundImage: UltrasoundImage? = null
) : BaseAuditEntity() {

    @PrePersist
    fun prePersist() {
        correctAnswer.testingQuestion = this
    }
}
