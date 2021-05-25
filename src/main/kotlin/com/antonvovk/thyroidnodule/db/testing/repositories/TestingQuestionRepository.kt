package com.antonvovk.thyroidnodule.db.testing.repositories

import com.antonvovk.thyroidnodule.db.testing.models.TestingQuestion
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TestingQuestionRepository : JpaRepository<TestingQuestion, Long>
