package com.antonvovk.thyroidnodule.db.users.mappers

import com.antonvovk.thyroidnodule.api.dto.UserDto
import com.antonvovk.thyroidnodule.common.TwoWayMapper
import com.antonvovk.thyroidnodule.db.users.models.User
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper(componentModel = "spring")
interface UserMapper : TwoWayMapper<User, UserDto> {

    @Mappings(
        Mapping(target = "firstName", source = "firstName"),
        Mapping(target = "lastName", source = "lastName"),
        Mapping(target = "middleName", source = "middleName"),
        Mapping(target = "workPlace", source = "workPlace"),
        Mapping(target = "email", source = "email"),
    )
    override fun map(from: User): UserDto

    @Mappings(
        Mapping(target = "groups", expression = "java(new ArrayList())")
    )
    override fun mapReverse(from: UserDto): User
}
