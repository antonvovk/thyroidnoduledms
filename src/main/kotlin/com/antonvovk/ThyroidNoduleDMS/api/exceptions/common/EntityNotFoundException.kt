package com.antonvovk.ThyroidNoduleDMS.api.exceptions.common

import java.util.*
import kotlin.reflect.KClass

class EntityNotFoundException(
    val id: String,
    val name: String,
) : RuntimeException() {

    constructor(id: UUID, name: KClass<*>) : this(id.toString(), name.toString())

    constructor(id: String, name: KClass<*>) : this(id, name.toString())
}
