package BatDongSan.example.BatDongSan.controller;

import BatDongSan.example.BatDongSan.dto.UserDto;
import BatDongSan.example.BatDongSan.exception.AppException;
import BatDongSan.example.BatDongSan.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @GetMapping("/list")
    public ResponseEntity<List<UserDto>> getAllUser(){
        try {
            List<UserDto> userDto = userService.getAllUsers();
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        }catch (AppException e){
            throw new AppException("NOT FOUND", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username) {
        UserDto userDtos = userService.getUserByUsername(username);
        if (userDtos != null){
            return new ResponseEntity<>(userDtos, HttpStatus.OK);
        }
        throw new AppException("NOT FOUND", HttpStatus.NOT_FOUND);
    }


}
