package com.antonvovk.thyroidnodule.services

import com.antonvovk.thyroidnodule.db.analyses.model.Analysis

interface NeuralNetworkService {

    fun predict(analysis: Analysis): String

    fun init()
}
