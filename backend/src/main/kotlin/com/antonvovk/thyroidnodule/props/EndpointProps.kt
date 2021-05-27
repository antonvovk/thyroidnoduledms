package com.antonvovk.thyroidnodule.props

class EndpointProps {
    lateinit var endpointPattern: String
    var allowedOrigins: List<String> = emptyList()
    var allowedMethods: List<String> = emptyList()
    var allowedHeaders: List<String> = emptyList()
    var exposedHeaders: List<String> = emptyList()
    var maxAge: Long = 60
    var allowedCredentials: Boolean = false
}
