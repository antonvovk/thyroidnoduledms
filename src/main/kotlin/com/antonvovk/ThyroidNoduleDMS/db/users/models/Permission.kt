package com.antonvovk.ThyroidNoduleDMS.db.users.models

import com.antonvovk.ThyroidNoduleDMS.db.models.BaseAuditEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.ManyToMany
import javax.persistence.Table

@Entity
@Table(name = "Permission", schema = "users")
data class Permission(

    @Column(name = "name")
    var name: String,

    @Column(name = "description")
    var description: String? = null
) : BaseAuditEntity() {

    @ManyToMany(mappedBy = "permissions")
    private val groups: List<Group> = emptyList()
}
