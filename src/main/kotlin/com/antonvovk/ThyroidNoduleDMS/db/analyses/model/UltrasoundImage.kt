package com.antonvovk.ThyroidNoduleDMS.db.analyses.model

import com.antonvovk.ThyroidNoduleDMS.db.models.BaseAuditEntity
import javax.persistence.*

@Entity
@Table(name = "UltrasoundImage", schema = "analyses")
data class UltrasoundImage(

    @Column(name = "filename")
    var filename: String,

    @Column(name = "height")
    var height: Int,

    @Column(name = "width")
    var width: Int
) : BaseAuditEntity() {

    @ManyToOne
    @JoinColumn(name = "ultrasoundAnalysisId")
    private lateinit var ultrasoundAnalysis: UltrasoundAnalysis
}
