package com.antonvovk.thyroidnodule.services.impl

import com.antonvovk.thyroidnodule.api.exceptions.common.EntityNotFoundException
import com.antonvovk.thyroidnodule.db.analyses.model.*
import com.antonvovk.thyroidnodule.db.analyses.repositories.AnalysisRepository
import com.antonvovk.thyroidnodule.db.users.models.User
import com.antonvovk.thyroidnodule.db.users.repositories.UserRepository
import com.antonvovk.thyroidnodule.services.ExelReaderService
import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class ExelReaderServiceImpl(
    private val userRepository: UserRepository,
    private val analysisRepository: AnalysisRepository
) : ExelReaderService {

    override fun loadFromExel(file: MultipartFile): MutableList<Analysis> {
        val username = SecurityContextHolder.getContext().authentication.principal as String
        val user = userRepository.findByEmail(username)
            ?: throw EntityNotFoundException(username, User::class)

        val workbook: Workbook = XSSFWorkbook(file.inputStream)
        val sheet = workbook.getSheetAt(0)
        val analyses = mutableListOf<Analysis>()

        for ((i, row) in sheet.withIndex()) {
            if (i >= 10) {
                var sex: Sex? = null
                var age: Int? = null
                var size: NoduleSize? = null
                var hasConglomerate = false
                var shape: NoduleShape? = null
                var contours: NoduleContours? = null
                var echogenicity: NoduleEchogenicity? = null
                var vascularization: NoduleVascularization? = null
                var elastography: NoduleElastography? = null
                var autoimmuneThyroiditis = false
                var suspiciousLymphNodes = false
                var thirads: Thirads? = null
                val structure: MutableList<NoduleStructure> = mutableListOf()
                var bethesdaLevel: BethesdaLevel? = null

                for ((j, cell) in row.withIndex()) {
                    val value = if (cell.cellType == CellType.NUMERIC) {
                        cell.numericCellValue.toString()
                    } else {
                        cell.stringCellValue
                    }

                    if (j == 1 && value == "+") {
                        sex = Sex.M
                    }
                    if (j == 2 && value == "+") {
                        sex = Sex.M
                    }
                    if (j == 3 && value != "") {
                        age = value.toFloat().toInt()
                    }
                    if (j == 4 && value == "+") {
                        size = NoduleSize.LESS_THAN_ONE
                    }
                    if (j == 5 && value == "+") {
                        size = NoduleSize.BETWEEN_ONE_AND_TWO
                    }
                    if (j == 6 && value == "+") {
                        size = NoduleSize.MORE_THAN_TWO
                    }
                    if (j == 7 && value == "+") {
                        hasConglomerate = true
                    }
                    if (j == 8 && value == "+") {
                        shape = NoduleShape.OVAL
                    }
                    if (j == 9 && value == "+") {
                        shape = NoduleShape.ROUND
                    }
                    if (j == 10 && value == "+") {
                        shape = NoduleShape.IRREGULAR
                    }
                    if (j == 11 && value == "+") {
                        contours = NoduleContours.CLEAR_EVEN
                    }
                    if (j == 12 && value == "+") {
                        contours = NoduleContours.CLEAR_UNEVEN
                    }
                    if (j == 13 && value == "+") {
                        contours = NoduleContours.FUZZY_EVEN
                    }
                    if (j == 14 && value == "+") {
                        contours = NoduleContours.FUZZY_UNEVEN
                    }
                    if (j == 15 && value == "+") {
                        structure.add(NoduleStructure.HOMOGENEOUS)
                    }
                    if (j == 16 && value == "+") {
                        structure.add(NoduleStructure.HETEROGENEOUS)
                    }
                    if (j == 17 && value == "+") {
                        structure.add(NoduleStructure.SPONGY)
                    }
                    if (j == 18 && value == "+") {
                        structure.add(NoduleStructure.INHOMOGENEOUS_DUE_TO_CENTRAL_CYSTS)
                    }
                    if (j == 19 && value == "+") {
                        structure.add(NoduleStructure.INHOMOGENEOUS_DUE_TO_PERIPHERAL_CYSTS)
                    }
                    if (j == 20 && value == "+") {
                        structure.add(NoduleStructure.MACROCALCINATES)
                    }
                    if (j == 21 && value == "+") {
                        structure.add(NoduleStructure.MICROCALCINATES)
                    }
                    if (j == 22 && value == "+") {
                        echogenicity = NoduleEchogenicity.HYPOECHOIC
                    }
                    if (j == 23 && value == "+") {
                        echogenicity = NoduleEchogenicity.ISOECHOIC
                    }
                    if (j == 24 && value == "+") {
                        vascularization = NoduleVascularization.CENTRAL
                    }
                    if (j == 25 && value == "+") {
                        vascularization = NoduleVascularization.PERIPHERAL
                    }
                    if (j == 26 && value == "+") {
                        vascularization = NoduleVascularization.MIXED
                    }
                    if (j == 27 && value == "+") {
                        elastography = NoduleElastography.TYPE2
                    }
                    if (j == 28 && value == "+") {
                        elastography = NoduleElastography.TYPE3
                    }
                    if (j == 29 && value == "+") {
                        elastography = NoduleElastography.TYPE4
                    }
                    if (j == 30 && value == "+") {
                        autoimmuneThyroiditis = true
                    }
                    if (j == 31 && value == "+") {
                        suspiciousLymphNodes = true
                    }
                    if (j == 34 && value == "+") {
                        thirads = Thirads.CLASS2
                    }
                    if (j == 35 && value == "+") {
                        thirads = Thirads.CLASS3
                    }
                    if (j == 36 && value == "+") {
                        thirads = Thirads.CLASS4
                    }
                    if (j == 37 && value == "+") {
                        bethesdaLevel = BethesdaLevel.CLASS1
                    }
                    if (j == 38 && value == "+") {
                        bethesdaLevel = BethesdaLevel.CLASS2
                    }
                    if (j == 39 && value == "+") {
                        bethesdaLevel = BethesdaLevel.CLASS3
                    }
                    if (j == 40 && value == "+") {
                        bethesdaLevel = BethesdaLevel.CLASS4
                    }
                    if (j == 41 && value == "+") {
                        bethesdaLevel = BethesdaLevel.CLASS5
                    }
                    if (j == 42 && value == "+") {
                        bethesdaLevel = BethesdaLevel.CLASS6
                    }
                }

                if (size == null || sex == null || age == null || bethesdaLevel == null || shape == null || contours == null || echogenicity == null || vascularization == null || elastography == null || thirads == null) {
                    continue
                }

                val patientInfo = PatientInfo(sex = sex, age = age)
                val biopsyAnalysis = BiopsyAnalysis(bethesdaLevel = bethesdaLevel)
                val ultrasoundAnalysis = UltrasoundAnalysis(
                    size,
                    hasConglomerate,
                    shape,
                    contours,
                    echogenicity,
                    vascularization,
                    elastography,
                    autoimmuneThyroiditis,
                    suspiciousLymphNodes,
                    thirads,
                    structure
                )
                val analysis = Analysis(
                    createdBy = user,
                    updatedBy = user,
                    patientInfo = patientInfo,
                    biopsyAnalysis = biopsyAnalysis,
                    ultrasoundAnalysis = ultrasoundAnalysis
                )

                analyses.add(analysis)
            }
        }

        return analysisRepository.saveAll(analyses)
    }
}
