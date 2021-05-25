package com.antonvovk.thyroidnodule.utils

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.reflect.full.companionObject

interface Logging

fun getLogger(forClass: Class<*>): Logger = LoggerFactory.getLogger(forClass)

@Suppress("NOTHING_TO_INLINE")
inline fun <T : Any> getClassForLogging(javaClass: Class<T>): Class<*> {
    return javaClass.enclosingClass?.takeIf {
        it.kotlin.companionObject?.java == javaClass
    } ?: javaClass
}

@Suppress("unused")
inline fun <reified T : Logging> T.logger(): Logger = getLogger(getClassForLogging(T::class.java))
