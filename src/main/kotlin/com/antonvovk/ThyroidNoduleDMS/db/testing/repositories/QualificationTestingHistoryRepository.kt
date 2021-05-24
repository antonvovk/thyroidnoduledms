package com.antonvovk.ThyroidNoduleDMS.db.testing.repositories

import com.antonvovk.ThyroidNoduleDMS.db.testing.models.QualificationTestingHistory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface QualificationTestingHistoryRepository : JpaRepository<QualificationTestingHistory, Long>
