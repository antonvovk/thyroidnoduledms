package com.antonvovk.thyroidnodule.common

import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.Mapper
import java.util.*

interface OneWayMapper<S, T> {
    fun map(from: S): T

    fun map(from: Collection<S>): List<T>
}

interface TwoWayMapper<S, T> : OneWayMapper<S, T> {
    @InheritInverseConfiguration
    fun mapReverse(from: T): S

    fun mapReverse(from: Collection<T>): List<S>
}

@Mapper(componentModel = "spring")
abstract class UUIDMapper : TwoWayMapper<UUID, String> {
    override fun map(from: UUID) = from.toString()

    override fun mapReverse(from: String): UUID = UUID.fromString(from)
}
