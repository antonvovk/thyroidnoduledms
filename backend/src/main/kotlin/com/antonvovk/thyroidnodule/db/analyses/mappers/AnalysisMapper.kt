package com.antonvovk.thyroidnodule.db.analyses.mappers

import com.antonvovk.thyroidnodule.api.dto.AnalysisDto
import com.antonvovk.thyroidnodule.common.TwoWayMapper
import com.antonvovk.thyroidnodule.db.analyses.model.Analysis
import com.antonvovk.thyroidnodule.db.users.mappers.UserMapper
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper(
    componentModel = "spring",
    uses = [UserMapper::class, PatientInfoMapper::class, BiopsyAnalysisMapper::class, UltrasoundAnalysisMapper::class]
)
interface AnalysisMapper : TwoWayMapper<Analysis, AnalysisDto> {

    @Mappings(
        Mapping(target = "createdBy", source = "createdBy"),
        Mapping(target = "updatedBy", source = "updatedBy"),
        Mapping(target = "patientInfo", source = "patientInfo"),
        Mapping(target = "biopsyAnalysis", source = "biopsyAnalysis"),
        Mapping(target = "ultrasoundAnalysis", source = "ultrasoundAnalysis")
    )
    override fun map(from: Analysis): AnalysisDto

    @Mappings(
        Mapping(target = "patientInfo", source = "patientInfo"),
        Mapping(target = "biopsyAnalysis", source = "biopsyAnalysis"),
        Mapping(target = "ultrasoundAnalysis", source = "ultrasoundAnalysis"),
        Mapping(target = "created", ignore = true),
        Mapping(target = "updated", ignore = true)
    )
    override fun mapReverse(from: AnalysisDto): Analysis
}
