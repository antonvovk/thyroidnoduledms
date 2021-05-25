package com.antonvovk.thyroidnodule.db.users.mappers

import com.antonvovk.thyroidnodule.api.dto.UserDto
import com.antonvovk.thyroidnodule.common.TwoWayMapper
import com.antonvovk.thyroidnodule.db.users.models.User
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface UserMapper : TwoWayMapper<User, UserDto>
