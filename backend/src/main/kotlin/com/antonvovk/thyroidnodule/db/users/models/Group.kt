package com.antonvovk.thyroidnodule.db.users.models

import com.antonvovk.thyroidnodule.db.models.BaseAuditEntity
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
        schema = "users",
        joinColumns = [JoinColumn(name = "groupId")],
        inverseJoinColumns = [JoinColumn(name = "permissionId")]
    )
    val permissions: MutableList<Permission> = mutableListOf()
) : BaseAuditEntity() {

    @ManyToMany(mappedBy = "groups")
    private val users: List<User> = emptyList()
}
