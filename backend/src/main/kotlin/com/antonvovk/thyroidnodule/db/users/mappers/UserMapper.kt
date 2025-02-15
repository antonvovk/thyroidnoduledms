package com.antonvovk.thyroidnodule.db.users.mappers

import com.antonvovk.thyroidnodule.api.dto.RegistrationDto
import com.antonvovk.thyroidnodule.api.dto.UserDto
import com.antonvovk.thyroidnodule.common.TwoWayMapper
import com.antonvovk.thyroidnodule.db.users.models.User
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import java.util.stream.Collectors

@Mapper(componentModel = "spring", imports = [Collectors::class])
interface UserMapper : TwoWayMapper<User, UserDto> {

    @Mappings(
        Mapping(target = "firstName", source = "firstName"),
        Mapping(target = "lastName", source = "lastName"),
        Mapping(target = "middleName", source = "middleName"),
        Mapping(target = "workPlace", source = "workPlace"),
        Mapping(target = "email", source = "email"),
        Mapping(target = "qualificationTestPassed", source = "qualificationTested"),
        Mapping(
            target = "permissions",
            expression = "java(from.getGroups().stream().flatMap(g -> g.getPermissions().stream()).map(p -> p.getName()).collect(Collectors.toList()))"
        )
    )
    override fun map(from: User): UserDto

    @Mappings(
        Mapping(target = "groups", expression = "java(new ArrayList())")
    )
    override fun mapReverse(from: UserDto): User

    @Mappings(
        Mapping(target = "firstName", source = "user.firstName"),
        Mapping(target = "lastName", source = "user.lastName"),
        Mapping(target = "middleName", source = "user.middleName"),
        Mapping(target = "workPlace", source = "user.workPlace"),
        Mapping(target = "email", source = "user.email"),
        Mapping(target = "passwordHash", source = "passwordHash"),
        Mapping(target = "groups", expression = "java(new ArrayList())")
    )
    fun map(from: RegistrationDto): User
}
