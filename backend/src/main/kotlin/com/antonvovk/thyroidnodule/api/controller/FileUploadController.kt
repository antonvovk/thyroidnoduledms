package com.antonvovk.thyroidnodule.api.controller

import com.antonvovk.thyroidnodule.api.exceptions.StorageFileNotFoundException
import com.antonvovk.thyroidnodule.services.StorageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.Resource
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@RestController
@RequestMapping("/api/files")
class FileUploadController @Autowired constructor(private val storageService: StorageService) {

    @GetMapping("/{filename}", produces = [MediaType.APPLICATION_OCTET_STREAM_VALUE])
    @ResponseBody
    fun serveFile(@PathVariable filename: String): ByteArray {
        val file: Resource = storageService.loadAsResource(filename)
        return file.file.readBytes()
    }

    @PostMapping
    fun handleFileUpload(
        @RequestParam("file") file: MultipartFile,
        redirectAttributes: RedirectAttributes
    ) {
        storageService.store(file)
    }

    @ExceptionHandler(StorageFileNotFoundException::class)
    fun handleStorageFileNotFound(exc: StorageFileNotFoundException?): ResponseEntity<*> {
        return ResponseEntity.notFound().build<Any>()
    }
}
