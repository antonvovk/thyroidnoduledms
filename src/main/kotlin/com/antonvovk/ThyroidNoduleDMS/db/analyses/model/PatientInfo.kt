package com.antonvovk.ThyroidNoduleDMS.db.analyses.model

import com.antonvovk.ThyroidNoduleDMS.db.models.BaseAuditEntity
import javax.persistence.*

@Entity
@Table(name = "PatientInfo", schema = "analyses")
data class PatientInfo(

    @Column(name = "sex", columnDefinition = "CHAR(1)")
    @Enumerated(EnumType.STRING)
    var sex: Sex,

    @Column(name = "age")
    var age: Int
) : BaseAuditEntity() {

    @OneToOne
    @JoinColumn(name = "analysisId")
    lateinit var analysis: Analysis
}

enum class Sex {
    M,
    F
}
