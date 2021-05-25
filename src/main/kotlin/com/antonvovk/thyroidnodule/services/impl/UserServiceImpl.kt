package com.antonvovk.thyroidnodule.services.impl

import com.antonvovk.thyroidnodule.db.users.models.User
import com.antonvovk.thyroidnodule.db.users.repositories.GroupRepository
import com.antonvovk.thyroidnodule.db.users.repositories.UserRepository
import com.antonvovk.thyroidnodule.services.UserService
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val groupRepository: GroupRepository
) : UserService {

    override fun register(user: User) {
        TODO("Not yet implemented")
    }
}
