package com.antonvovk.thyroidnodule.security.services.impl

import com.antonvovk.thyroidnodule.api.exceptions.common.EntityNotFoundException
import com.antonvovk.thyroidnodule.db.analyses.model.Analysis
import com.antonvovk.thyroidnodule.db.analyses.repositories.AnalysisRepository
import com.antonvovk.thyroidnodule.security.services.AnalysisService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class AnalysisServiceImpl(
    private val analysisRepository: AnalysisRepository
) : AnalysisService {

    override fun getAll(): List<Analysis> = analysisRepository.findAll()

    override fun create(analysis: Analysis) {
        analysisRepository.save(analysis)
    }

    override fun update(analysis: Analysis) {
        val entity = analysisRepository.findByIdOrNull(analysis.id) ?: throw EntityNotFoundException(
            analysis.id,
            Analysis::class
        )

        entity.patientInfo.age = analysis.patientInfo.age
        entity.patientInfo.sex = analysis.patientInfo.sex
        entity.biopsyAnalysis.bethesdaLevel = analysis.biopsyAnalysis.bethesdaLevel
        entity.ultrasoundAnalysis.size = analysis.ultrasoundAnalysis.size
        entity.ultrasoundAnalysis.hasConglomerate = analysis.ultrasoundAnalysis.hasConglomerate
        entity.ultrasoundAnalysis.shape = analysis.ultrasoundAnalysis.shape
        entity.ultrasoundAnalysis.contours = analysis.ultrasoundAnalysis.contours
        entity.ultrasoundAnalysis.echogenicity = analysis.ultrasoundAnalysis.echogenicity
        entity.ultrasoundAnalysis.vascularization = analysis.ultrasoundAnalysis.vascularization
        entity.ultrasoundAnalysis.elastography = analysis.ultrasoundAnalysis.elastography
        entity.ultrasoundAnalysis.autoimmuneThyroiditis = analysis.ultrasoundAnalysis.autoimmuneThyroiditis
        entity.ultrasoundAnalysis.suspiciousLymphNodes = analysis.ultrasoundAnalysis.suspiciousLymphNodes
        entity.ultrasoundAnalysis.thirads = analysis.ultrasoundAnalysis.thirads
        entity.ultrasoundAnalysis.structure = analysis.ultrasoundAnalysis.structure

        analysisRepository.save(entity)
    }
}
