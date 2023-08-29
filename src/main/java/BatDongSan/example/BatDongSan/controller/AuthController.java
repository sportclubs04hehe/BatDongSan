package BatDongSan.example.BatDongSan.controller;

import BatDongSan.example.BatDongSan.config.UserAuthenticationProvider;
import BatDongSan.example.BatDongSan.dto.CredentialsDto;
import BatDongSan.example.BatDongSan.dto.SignUpDto;
import BatDongSan.example.BatDongSan.dto.UpdateUserDto;
import BatDongSan.example.BatDongSan.dto.UserDto;
import BatDongSan.example.BatDongSan.exception.AppException;
import BatDongSan.example.BatDongSan.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(path = "/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final UserService userService;
    private final UserAuthenticationProvider userAuthenticationProvider;

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody @Valid CredentialsDto credentialsDto) {
        log.info("Điểm cuối đăng nhập bằng tên người dùng: {}", credentialsDto.username());
        UserDto userDto = userService.login(credentialsDto);
        userDto.setPassword(userAuthenticationProvider.createToken(userDto));
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody @Valid SignUpDto user) {
        log.info("Register endpoint hit with username: {}", user.username());
        UserDto createdUser = userService.register(user);
        createdUser.setPassword(userAuthenticationProvider.createToken(createdUser));
        return ResponseEntity.created(URI.create("/users/" + createdUser.getUserID())).body(createdUser);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UpdateUserDto updateUserDto) {
        log.info("Update với người dùng có id= {}", id);
        try {
            UserDto updateUser = userService.updateUser(updateUserDto, id);
            return new ResponseEntity<>(updateUser, HttpStatus.OK);
        } catch (Exception ex) {
            throw new AppException("Có lỗi xảy ra", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (AppException e) {
            if (e.getHttpStatus() == HttpStatus.NOT_FOUND) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
