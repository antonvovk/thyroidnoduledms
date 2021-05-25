package com.antonvovk.thyroidnodule

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan(basePackages = ["com.antonvovk.thyroidnodule.config"])
class ThyroidNoduleDmsApplication

fun main(args: Array<String>) {
    runApplication<ThyroidNoduleDmsApplication>(*args)
}
