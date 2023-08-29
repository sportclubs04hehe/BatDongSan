package BatDongSan.example.BatDongSan.service;

import BatDongSan.example.BatDongSan.dto.CredentialsDto;
import BatDongSan.example.BatDongSan.dto.SignUpDto;
import BatDongSan.example.BatDongSan.dto.UpdateUserDto;
import BatDongSan.example.BatDongSan.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto login(CredentialsDto credentialsDto);

    UserDto register(SignUpDto signUpDto);

    UserDto findByLogin(String login);

    UserDto updateUser(UpdateUserDto updateUserDto, Long id);
    List<UserDto> getAllUsers();
    UserDto getUserByUsername(String username);
    void deleteUser(Long id);

}
