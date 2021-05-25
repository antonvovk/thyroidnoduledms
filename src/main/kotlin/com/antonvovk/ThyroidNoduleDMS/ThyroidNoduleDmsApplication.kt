package com.antonvovk.ThyroidNoduleDMS

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan(basePackages = ["com.antonvovk.ThyroidNoduleDMS.config"])
class ThyroidNoduleDmsApplication

fun main(args: Array<String>) {
    runApplication<ThyroidNoduleDmsApplication>(*args)
}
