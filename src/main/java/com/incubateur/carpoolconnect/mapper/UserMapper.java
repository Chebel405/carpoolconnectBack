package com.incubateur.carpoolconnect.mapper;

import com.incubateur.carpoolconnect.dto.UserDto;
import com.incubateur.carpoolconnect.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User userDtoToUser(UserDto userDto);

    UserDto userToUserDto(User user);
}
