package com.antonvovk.thyroidnodule.db.testing.repositories

import com.antonvovk.thyroidnodule.db.testing.models.QualificationTestingResult
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface QualificationTestingResultRepository : JpaRepository<QualificationTestingResult, Long>
