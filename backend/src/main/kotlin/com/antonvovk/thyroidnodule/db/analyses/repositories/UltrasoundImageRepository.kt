package com.antonvovk.thyroidnodule.db.analyses.repositories

import com.antonvovk.thyroidnodule.db.analyses.model.UltrasoundImage
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UltrasoundImageRepository : JpaRepository<UltrasoundImage, Long>
