package com.antonvovk.thyroidnodule.db.testing.models

import com.antonvovk.thyroidnodule.db.analyses.model.UltrasoundImage
import com.antonvovk.thyroidnodule.db.models.BaseAuditEntity
import javax.persistence.*

@Entity
@Table(name = "TestingQuestion", schema = "testing")
data class QualificationQuestion(

    @Column(name = "questionText")
    var questionText: String,

    @OneToOne(mappedBy = "testingQuestion", cascade = [CascadeType.ALL])
    val correctAnswer: QualificationAnswer,

    @OneToOne
    @JoinColumn(name = "ultrasoundImageId")
    var ultrasoundImage: UltrasoundImage? = null
) : BaseAuditEntity() {

    @PrePersist
    fun prePersist() {
        correctAnswer.testingQuestion = this
    }
}
