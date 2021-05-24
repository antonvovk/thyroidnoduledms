package com.antonvovk.ThyroidNoduleDMS.db.testing.repositories

import com.antonvovk.ThyroidNoduleDMS.db.testing.models.TestingQuestion
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TestingQuestionRepository : JpaRepository<TestingQuestion, Long>
