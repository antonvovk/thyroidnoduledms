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

    @ElementCollection
    @CollectionTable(
        schema = "analyses",
        name = "NoduleStructure",
        joinColumns = [JoinColumn(name = "ultrasoundAnalysisId", nullable = false)]
    )
    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    val structure: MutableList<NoduleStructure> = mutableListOf(),

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
    AA
}

enum class NoduleShape {
    AA
}

enum class NoduleContours {
    AA
}

enum class NoduleEchogenicity {
    AA
}

enum class NoduleVascularization {
    AA
}

enum class NoduleElastography {
    AA
}

enum class Thirads {
    AA
}

enum class NoduleStructure {
    AA
}
