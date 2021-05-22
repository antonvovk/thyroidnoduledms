package com.antonvovk.ThyroidNoduleDMS.db.users.models

import com.antonvovk.ThyroidNoduleDMS.db.models.BaseAuditEntity
import javax.persistence.*

@Entity
@Table(name = "User", schema = "users")
data class User(

    @Column(name = "firstName")
    var firstName: String,

    @Column(name = "lastName")
    var lastName: String,

    @Column(name = "middleName")
    var middleName: String,

    @Column(name = "workPlace")
    var workPlace: String,

    @Column(name = "email")
    var email: String,

    @Column(name = "passwordHash")
    var passwordHash: String,

    @Column(name = "isQualificationTested")
    var isQualificationTested: Boolean = false,

    @ManyToMany
    @JoinTable(
        name = "UserGroup",
        joinColumns = [JoinColumn(name = "userId")],
        inverseJoinColumns = [JoinColumn(name = "groupId")]
    )
    val groups: MutableList<Group> = mutableListOf()
) : BaseAuditEntity()
