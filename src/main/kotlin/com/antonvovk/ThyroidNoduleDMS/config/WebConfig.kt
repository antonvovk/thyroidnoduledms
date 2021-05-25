package com.antonvovk.ThyroidNoduleDMS.config

import com.antonvovk.ThyroidNoduleDMS.props.EndpointProps
import com.antonvovk.ThyroidNoduleDMS.utils.Logging
import com.antonvovk.ThyroidNoduleDMS.utils.logger
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@ConfigurationProperties(prefix = "cors-mapping")
class WebConfig : WebMvcConfigurer {

    lateinit var endpoints: List<EndpointProps>

    override fun addCorsMappings(registry: CorsRegistry) {

        for ((index, props) in endpoints.withIndex()) {
            val registration: CorsRegistration = registry.addMapping(props.endpointPattern)
            if (props.allowedOrigins.isNotEmpty()) {
                registration.allowedOrigins(*props.allowedOrigins.toTypedArray())
            } else {
                log.warn("cors-policy.endpoint[{}]: allowed origins are not defined!", index)
            }
            if (props.allowedMethods.isNotEmpty()) {
                registration.allowedMethods(*props.allowedMethods.toTypedArray())
            } else {
                log.warn("cors-policy.endpoint[{}]: allowed methods are not defined!", index)
            }
            if (props.allowedHeaders.isNotEmpty()) {
                registration.allowedHeaders(*props.allowedHeaders.toTypedArray())
            } else {
                log.warn("cors-policy.endpoint[{}]: allowed headers are not defined!", index)
            }
            if (props.exposedHeaders.isNotEmpty()) {
                registration.exposedHeaders(*props.exposedHeaders.toTypedArray())
            } else {
                log.warn("cors-policy.endpoint[{}]: exposed headers are not defined!", index)
            }
            registration.allowCredentials(props.allowedCredentials)
            registration.maxAge(props.maxAge)

        }
    }

    companion object : Logging {
        private val log = logger()
    }
}
