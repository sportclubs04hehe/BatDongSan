package BatDongSan.example.BatDongSan.mapper;

import BatDongSan.example.BatDongSan.dto.SignUpDto;
import BatDongSan.example.BatDongSan.dto.UserDto;
import BatDongSan.example.BatDongSan.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toUserDto(User user);

    @Mapping(target = "password", ignore = true)
    User signUpToUser(SignUpDto signUpDto);
}
