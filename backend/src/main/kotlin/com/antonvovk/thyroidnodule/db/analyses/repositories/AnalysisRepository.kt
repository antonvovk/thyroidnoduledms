package com.antonvovk.thyroidnodule.db.analyses.repositories

import com.antonvovk.thyroidnodule.db.analyses.model.Analysis
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AnalysisRepository : JpaRepository<Analysis, Long>
