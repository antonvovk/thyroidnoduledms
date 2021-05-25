package com.antonvovk.thyroidnodule.db.analyses.model

import com.antonvovk.thyroidnodule.db.models.BaseAuditEntity
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
    lateinit var analysis: Analysis
}

enum class BethesdaLevel {
    Class2,
    Class3,
    Class4
}
