package com.antonvovk.ThyroidNoduleDMS.db.analyses.model

import com.antonvovk.ThyroidNoduleDMS.db.models.BaseAuditEntity
import javax.persistence.*

@Entity
@Table(name = "BiopsyAnalysis", schema = "analyses")
data class BiopsyAnalysis(

    @Column(name = "bethesdaLevel")
    @Enumerated(EnumType.STRING)
    var bethesdaLevel: BethesdaLevel
) : BaseAuditEntity() {

    @OneToOne
    @JoinColumn(name = "analysisId")
    private lateinit var analysis: Analysis
}

enum class BethesdaLevel {
    Class2,
    Class3,
    Class4
}
