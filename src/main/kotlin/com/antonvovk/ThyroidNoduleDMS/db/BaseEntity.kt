package com.antonvovk.ThyroidNoduleDMS.db.models

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import org.springframework.data.util.ProxyUtils
import java.time.LocalDateTime
import javax.persistence.*

@MappedSuperclass
abstract class BaseEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    // Method is final so that data classes could not automatically override it
    final override fun equals(other: Any?): Boolean {
        other ?: return false

        if (this === other) return true

        if (javaClass != ProxyUtils.getUserClass(other)) return false

        return id == (other as BaseEntity).id
    }

    abstract override fun hashCode(): Int
}

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseAuditEntity : BaseEntity() {

    @CreationTimestamp
    @Column(name = "created", updatable = false, nullable = false)
    lateinit var created: LocalDateTime

    @UpdateTimestamp
    @Column(name = "updated", nullable = false)
    lateinit var updated: LocalDateTime
}
