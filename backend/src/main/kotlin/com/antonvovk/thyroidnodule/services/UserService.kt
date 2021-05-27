package com.antonvovk.thyroidnodule.services

import com.antonvovk.thyroidnodule.db.users.models.User

interface UserService {

    fun register(user: User)
}
