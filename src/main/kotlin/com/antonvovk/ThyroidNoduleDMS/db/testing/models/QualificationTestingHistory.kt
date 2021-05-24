package com.antonvovk.ThyroidNoduleDMS.db.testing.models

import com.antonvovk.ThyroidNoduleDMS.db.models.BaseAuditEntity
import com.antonvovk.ThyroidNoduleDMS.db.users.models.User
import javax.persistence.*

@Entity
@Table(name = "QualificationTestingHistory", schema = "testing")
data class QualificationTestingHistory(

    @ManyToOne
    val user: User,

    @Column(name = "scoredPercentage")
    val scoredPercentage: Float,

    @Column(name = "isPassed")
    val isPassed: Boolean,

    @OneToMany(mappedBy = "qualificationTestingHistory", cascade = [CascadeType.ALL])
    val answeredQuestions: List<AnsweredQuestion>
) : BaseAuditEntity() {

    @PrePersist
    fun prePersist() {
        answeredQuestions.forEach { it.qualificationTestingHistory = this }
    }
}
