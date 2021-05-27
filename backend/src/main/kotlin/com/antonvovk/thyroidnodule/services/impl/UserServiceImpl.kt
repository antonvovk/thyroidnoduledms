package com.antonvovk.thyroidnodule.services.impl

import com.antonvovk.thyroidnodule.api.exceptions.common.EntityNotFoundException
import com.antonvovk.thyroidnodule.db.users.models.Group
import com.antonvovk.thyroidnodule.db.users.models.User
import com.antonvovk.thyroidnodule.db.users.repositories.GroupRepository
import com.antonvovk.thyroidnodule.db.users.repositories.UserRepository
import com.antonvovk.thyroidnodule.services.UserService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val groupRepository: GroupRepository,
    private val passwordEncoder: PasswordEncoder
) : UserService {

    override fun register(user: User) {
        val group = groupRepository.findByName("Specialists")
            ?: throw EntityNotFoundException("Specialists", Group::class)

        user.groups = mutableListOf(group)
        userRepository.save(user)
    }
}
