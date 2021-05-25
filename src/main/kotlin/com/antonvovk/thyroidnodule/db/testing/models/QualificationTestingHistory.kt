package com.antonvovk.thyroidnodule.db.testing.models

import com.antonvovk.thyroidnodule.db.models.BaseAuditEntity
import com.antonvovk.thyroidnodule.db.users.models.User
import javax.persistence.*

@Entity
@Table(name = "QualificationTestingHistory", schema = "testing")
data class QualificationTestingHistory(

    @ManyToOne
    @JoinColumn(name = "userId")
    val user: User,

    @Column(name = "scoredPercentage", columnDefinition = "DECIMAL")
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
