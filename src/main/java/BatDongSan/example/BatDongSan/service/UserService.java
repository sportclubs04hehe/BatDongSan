package BatDongSan.example.BatDongSan.service;

import BatDongSan.example.BatDongSan.dto.CredentialsDto;
import BatDongSan.example.BatDongSan.dto.SignUpDto;
import BatDongSan.example.BatDongSan.dto.UserDto;

public interface UserService {
    UserDto login(CredentialsDto credentialsDto);

    UserDto register(SignUpDto signUpDto);

    UserDto findByLogin(String login);

    UserDto findByUsername(String username);

}
