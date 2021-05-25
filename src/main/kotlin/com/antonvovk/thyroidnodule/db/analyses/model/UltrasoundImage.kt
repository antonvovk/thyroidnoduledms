package com.antonvovk.thyroidnodule.db.analyses.model

import com.antonvovk.thyroidnodule.db.models.BaseAuditEntity
import com.antonvovk.thyroidnodule.db.testing.models.QualificationTestingQuestion
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
    lateinit var ultrasoundAnalysis: UltrasoundAnalysis

    @OneToOne(mappedBy = "ultrasoundImage")
    val testingQuestion: QualificationTestingQuestion? = null
}
