package com.antonvovk.thyroidnodule.services.impl

import com.antonvovk.thyroidnodule.api.exceptions.common.EntityNotFoundException
import com.antonvovk.thyroidnodule.db.analyses.model.Analysis
import com.antonvovk.thyroidnodule.db.analyses.model.UltrasoundImage
import com.antonvovk.thyroidnodule.db.analyses.repositories.AnalysisRepository
import com.antonvovk.thyroidnodule.db.analyses.repositories.UltrasoundImageRepository
import com.antonvovk.thyroidnodule.db.users.models.User
import com.antonvovk.thyroidnodule.db.users.repositories.UserRepository
import com.antonvovk.thyroidnodule.services.AnalysisService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class AnalysisServiceImpl(
    private val analysisRepository: AnalysisRepository,
    private val userRepository: UserRepository,
    private val ultrasoundImageRepository: UltrasoundImageRepository
) : AnalysisService {

    override fun getAll(): List<Analysis> = analysisRepository.findAll()

    override fun create(analysis: Analysis) {
        val username = SecurityContextHolder.getContext().authentication.principal as String
        val user = userRepository.findByEmail(username)
            ?: throw EntityNotFoundException(username, User::class)

        analysisRepository.save(analysis.apply {
            createdBy = user
            updatedBy = user
        })
    }

    override fun update(analysis: Analysis) {
        val entity = analysisRepository.findByIdOrNull(analysis.id) ?: throw EntityNotFoundException(
            analysis.id,
            Analysis::class
        )

        val username = SecurityContextHolder.getContext().authentication.principal as String
        val user = userRepository.findByEmail(username)
            ?: throw EntityNotFoundException(username, User::class)

        entity.updatedBy = user
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

    override fun addImage(id: Long, image: UltrasoundImage): Long {
        val entity = analysisRepository.findByIdOrNull(id)
            ?: throw EntityNotFoundException(id, Analysis::class)

        image.ultrasoundAnalysis = entity.ultrasoundAnalysis
        return ultrasoundImageRepository.save(image).id
    }

    override fun removeImage(id: Long, image: UltrasoundImage) {
        ultrasoundImageRepository.deleteById(image.id)
    }
}
