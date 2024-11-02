package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserDetailsDto;
import com.capgemini.wsb.fitnesstracker.user.api.UserIdEmailDto;
import com.capgemini.wsb.fitnesstracker.user.api.UserSimpleDto;
import org.springframework.stereotype.Component;

@Component
class UserMapper {

    UserDto toDto(User user) {
        return new UserDto(user.getId(),
                           user.getFirstName(),
                           user.getLastName(),
                           user.getBirthdate(),
                           user.getEmail());
    }

    User toEntity(UserDto userDto) {
        return new User(
                        userDto.firstName(),
                        userDto.lastName(),
                        userDto.birthdate(),
                        userDto.email());
    }

    UserSimpleDto toSimpleDto(User user) {
        return new UserSimpleDto(user.getId(), user.getFirstName(), user.getLastName());
    }

    UserDetailsDto toDetailsDto(User user) {
        return new UserDetailsDto(user.getFirstName(), user.getBirthdate(), user.getEmail());
    }

    UserIdEmailDto toUserIdEmailDto(User user) {
        return new UserIdEmailDto(user.getId(), user.getEmail());
    }
}
