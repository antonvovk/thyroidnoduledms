package com.antonvovk.ThyroidNoduleDMS.db.models

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*

@MappedSuperclass
abstract class BaseEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    abstract override fun equals(other: Any?): Boolean

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
