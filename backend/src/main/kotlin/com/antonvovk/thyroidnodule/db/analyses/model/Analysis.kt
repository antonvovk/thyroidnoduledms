package com.antonvovk.thyroidnodule.db.analyses.model

import com.antonvovk.thyroidnodule.db.models.BaseAuditEntity
import com.antonvovk.thyroidnodule.db.users.models.User
import javax.persistence.*

@Entity
@Table(name = "Analysis", schema = "analyses")
data class Analysis(

    @ManyToOne
    @JoinColumn(name = "createdBy")
    var createdBy: User,

    @ManyToOne
    @JoinColumn(name = "updatedBy")
    var updatedBy: User,

    @OneToOne(mappedBy = "analysis", cascade = [CascadeType.ALL])
    val patientInfo: PatientInfo,

    @OneToOne(mappedBy = "analysis", cascade = [CascadeType.ALL])
    val biopsyAnalysis: BiopsyAnalysis,

    @OneToOne(mappedBy = "analysis", cascade = [CascadeType.ALL])
    val ultrasoundAnalysis: UltrasoundAnalysis
) : BaseAuditEntity() {

    @PrePersist
    fun prePersist() {
        patientInfo.analysis = this
        biopsyAnalysis.analysis = this
        ultrasoundAnalysis.analysis = this
    }
}
