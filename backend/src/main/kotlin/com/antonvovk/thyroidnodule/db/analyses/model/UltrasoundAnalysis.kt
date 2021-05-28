package com.antonvovk.thyroidnodule.db.analyses.model

import com.antonvovk.thyroidnodule.db.models.BaseAuditEntity
import javax.persistence.*

@Entity
@Table(name = "UltrasoundAnalysis", schema = "analyses")
data class UltrasoundAnalysis(

    @Column(name = "size")
    @Enumerated(EnumType.STRING)
    var size: NoduleSize,

    @Column(name = "hasConglomerate")
    var hasConglomerate: Boolean,

    @Column(name = "shape")
    @Enumerated(EnumType.STRING)
    var shape: NoduleShape,

    @Column(name = "contours")
    @Enumerated(EnumType.STRING)
    var contours: NoduleContours,

    @Column(name = "echogenicity")
    @Enumerated(EnumType.STRING)
    var echogenicity: NoduleEchogenicity,

    @Column(name = "vascularization")
    @Enumerated(EnumType.STRING)
    var vascularization: NoduleVascularization,

    @Column(name = "elastography")
    @Enumerated(EnumType.STRING)
    var elastography: NoduleElastography,

    @Column(name = "autoimmuneThyroiditis")
    var autoimmuneThyroiditis: Boolean,

    @Column(name = "suspiciousLymphNodes")
    var suspiciousLymphNodes: Boolean,

    @Column(name = "thirads")
    @Enumerated(EnumType.STRING)
    var thirads: Thirads,

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
        schema = "analyses",
        name = "NoduleStructure",
        joinColumns = [JoinColumn(name = "ultrasoundAnalysisId", nullable = false)]
    )
    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    var structure: MutableList<NoduleStructure> = mutableListOf(),

    @OneToMany(mappedBy = "ultrasoundAnalysis", cascade = [CascadeType.ALL])
    val images: MutableList<UltrasoundImage> = mutableListOf()
) : BaseAuditEntity() {

    @OneToOne
    @JoinColumn(name = "analysisId")
    lateinit var analysis: Analysis

    @PrePersist
    fun prePersist() {
        images.forEach { it.ultrasoundAnalysis = this }
    }
}

enum class NoduleSize {
    LESS_THAN_ONE,
    BETWEEN_ONE_AND_TWO,
    MORE_THAN_TWO
}

enum class NoduleShape {
    OVAL,
    ROUND,
    VERTICAL,
    IRREGULAR
}

enum class NoduleContours {
    CLEAR_EVEN,
    CLEAR_UNEVEN,
    FUZZY_EVEN,
    FUZZY_UNEVEN
}

enum class NoduleEchogenicity {
    HYPOECHOIC,
    ISOECHOIC
}

enum class NoduleVascularization {
    CENTRAL,
    PERIPHERAL,
    MIXED
}

enum class NoduleElastography {
    TYPE2,
    TYPE3,
    TYPE4
}

enum class Thirads {
    CLASS2,
    CLASS3,
    CLASS4
}

enum class NoduleStructure {
    HOMOGENEOUS,
    HETEROGENEOUS,
    SPONGY,
    INHOMOGENEOUS_DUE_TO_CENTRAL_CYSTS,
    INHOMOGENEOUS_DUE_TO_PERIPHERAL_CYSTS,
    MACROCALCINATES,
    MICROCALCINATES
}
