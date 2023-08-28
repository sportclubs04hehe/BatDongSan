package BatDongSan.example.BatDongSan.service.impl;

import BatDongSan.example.BatDongSan.config.PasswordConfig;
import BatDongSan.example.BatDongSan.dto.CredentialsDto;
import BatDongSan.example.BatDongSan.dto.SignUpDto;
import BatDongSan.example.BatDongSan.dto.UserDto;
import BatDongSan.example.BatDongSan.entity.Role;
import BatDongSan.example.BatDongSan.entity.User;
import BatDongSan.example.BatDongSan.exception.AppException;
import BatDongSan.example.BatDongSan.mapper.UserMapper;
import BatDongSan.example.BatDongSan.repo.UserRepository;
import BatDongSan.example.BatDongSan.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final UserMapper userMapper;
    @Override
    public UserDto login(CredentialsDto credentialsDto) {
        log.info("Đang cố gắng đăng nhập với tên người dùng: {}",credentialsDto.username());
        User user = userRepository.findUserByUsername(credentialsDto.username())
                .orElseThrow(() -> new AppException("user not found", NOT_FOUND));
        if(passwordEncoder.matches(CharBuffer.wrap(credentialsDto.password()),user.getPassword())){
            log.info("Đăng nhập thành công với tên người dùng: {}", credentialsDto.username());
            return userMapper.toUserDto(user);
        }
        log.warn("Mật khẩu không hợp lệ cho tên người dùng: {}", credentialsDto.username());
        throw new AppException("Invalid password", BAD_REQUEST);
    }

    @Override
    public UserDto register(SignUpDto signUpDto) {
        log.info("Đang cố gắng đăng ký người dùng bằng tên người dùng: {}", signUpDto.username());
        if (signUpDto.email().contains(" ") || signUpDto.username().contains(" ") || String.valueOf(signUpDto.password()).contains(" ")){
            throw new AppException("Các trường username, email, password không được chứa dấu cách", BAD_REQUEST);
        }
        Optional<User> optionalUser = userRepository.findUserByUsername(signUpDto.username());
        if(optionalUser.isPresent()){
            throw  new AppException("login already exists", BAD_REQUEST);
        }
        User user = userMapper.signUpToUser(signUpDto);
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(signUpDto.password())));
        user.setRole(Role.USER);
        User savedUser = userRepository.save(user);
        log.info("Người dùng đã đăng ký thành công với id: {}", savedUser.getUserID());
        return userMapper.toUserDto(savedUser);
    }

    @Override
    public UserDto findByLogin(String login) {
        User user = userRepository.findUserByUsername(login)
                .orElseThrow(() -> new AppException("user not found", NOT_FOUND));
        return userMapper.toUserDto(user);
    }

    @Override
    public UserDto findByUsername(String username) {
        return null;
    }
}
