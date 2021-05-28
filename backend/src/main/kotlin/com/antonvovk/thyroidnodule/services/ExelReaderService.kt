package com.antonvovk.thyroidnodule.services

import com.antonvovk.thyroidnodule.db.analyses.model.Analysis
import org.springframework.web.multipart.MultipartFile

interface ExelReaderService {

    fun loadFromExel(file: MultipartFile): MutableList<Analysis>
}
