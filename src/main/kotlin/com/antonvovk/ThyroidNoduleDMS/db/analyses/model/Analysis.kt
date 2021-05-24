package com.antonvovk.ThyroidNoduleDMS.db.analyses.model

import com.antonvovk.ThyroidNoduleDMS.db.models.BaseAuditEntity
import com.antonvovk.ThyroidNoduleDMS.db.users.models.User
import javax.persistence.*

@Entity
@Table(name = "Analysis", schema = "analyses")
data class Analysis(

    @ManyToOne
    @JoinColumn(name = "createdBy")
    val createdBy: User,

    @ManyToOne
    @JoinColumn(name = "updatedBy")
    var updatedBy: User,

    @OneToOne(mappedBy = "analysis")
    val patientInfo: PatientInfo,

    @OneToOne(mappedBy = "analysis")
    val biopsyAnalysis: BiopsyAnalysis,

    @OneToOne(mappedBy = "analysis")
    val ultrasoundAnalysis: UltrasoundAnalysis
) : BaseAuditEntity()
