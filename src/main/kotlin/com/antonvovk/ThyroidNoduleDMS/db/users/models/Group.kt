package com.antonvovk.ThyroidNoduleDMS.db.users.models

import com.antonvovk.ThyroidNoduleDMS.db.models.BaseAuditEntity
import javax.persistence.*

@Entity
@Table(name = "[Group]", schema = "users")
data class Group(

    @Column(name = "name")
    var name: String,

    @Column(name = "description")
    var description: String? = null,

    @Column(name = "isDisabled")
    var isDisabled: Boolean = false,

    @ManyToMany
    @JoinTable(
        name = "GroupPermission",
        joinColumns = [JoinColumn(name = "groupId")],
        inverseJoinColumns = [JoinColumn(name = "permissionId")]
    )
    val permissions: MutableList<Permission> = mutableListOf()
) : BaseAuditEntity() {

    @ManyToMany(mappedBy = "groups")
    private val users: List<User> = emptyList()
}
