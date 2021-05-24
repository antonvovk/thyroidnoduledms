package com.antonvovk.ThyroidNoduleDMS.db.analyses.repositories

import com.antonvovk.ThyroidNoduleDMS.db.analyses.model.Analysis
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AnalysisRepository : JpaRepository<Analysis, Long>
