package com.antonvovk.thyroidnodule.api.exceptions.common

import java.util.*
import kotlin.reflect.KClass

class EntityNotFoundException(
    val id: String,
    val name: String,
) : RuntimeException() {

    constructor(id: UUID, name: KClass<*>) : this(id.toString(), name.toString())

    constructor(id: String, name: KClass<*>) : this(id, name.toString())

    constructor(id: Long, name: KClass<*>) : this(id.toString(), name.toString())
}
