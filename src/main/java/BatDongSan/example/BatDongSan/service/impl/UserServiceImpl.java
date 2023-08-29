package BatDongSan.example.BatDongSan.service.impl;

import BatDongSan.example.BatDongSan.dto.CredentialsDto;
import BatDongSan.example.BatDongSan.dto.SignUpDto;
import BatDongSan.example.BatDongSan.dto.UpdateUserDto;
import BatDongSan.example.BatDongSan.dto.UserDto;
import BatDongSan.example.BatDongSan.entity.Role;
import BatDongSan.example.BatDongSan.entity.User;
import BatDongSan.example.BatDongSan.exception.AppException;
import BatDongSan.example.BatDongSan.mapper.UserMapper;
import BatDongSan.example.BatDongSan.repo.UserRepository;
import BatDongSan.example.BatDongSan.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto login(CredentialsDto credentialsDto) {
        log.info("Đang cố gắng đăng nhập với tên người dùng: {}", credentialsDto.username());
        User user = userRepository.findUserByUsername(credentialsDto.username())
                .orElseThrow(() -> new AppException("user not found", NOT_FOUND));
        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.password()), user.getPassword())) {
            log.info("Đăng nhập thành công với tên người dùng: {}", credentialsDto.username());
            return userMapper.toUserDto(user);
        }
        log.warn("Mật khẩu không hợp lệ cho tên người dùng: {}", credentialsDto.username());
        throw new AppException("Invalid password", BAD_REQUEST);
    }

    @Override
    public UserDto register(SignUpDto signUpDto) {
        log.info("Đang cố gắng đăng ký người dùng bằng tên người dùng: {}", signUpDto.username());
        if (signUpDto.username().contains(" ") || String.valueOf(signUpDto.password()).contains(" ")) {
            throw new AppException("Các trường username, password không được chứa dấu cách", BAD_REQUEST);
        }
        Optional<User> optionalUser = userRepository.findUserByUsername(signUpDto.username());
        if (optionalUser.isPresent()) {
            throw new AppException("login already exists", BAD_REQUEST);
        }
        User user = userMapper.signUpToUser(signUpDto);
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(signUpDto.password())));
        user.setRole(Role.USER);
        User savedUser = userRepository.save(user);
        log.info("Người dùng đã đăng ký thành công với id: {}", savedUser.getUserID());
        return userMapper.toUserDto(savedUser);
    }

    @Override
    public UserDto updateUser(UpdateUserDto updateUserDto, Long id) {
        log.info("Đang cập nhật người dùng có id: {}", id);
        User user = userRepository.findById(id).orElseThrow(() ->
                new AppException("Người dùng không tồn tại", NOT_FOUND));
        if (updateUserDto.getUsername() != null) {
            Optional<User> optionalUser = userRepository.findUserByUsername(updateUserDto.getUsername());
            if (optionalUser.isPresent() && !Objects.equals(optionalUser.get().getUserID(), id)) {
                throw new AppException("Người dùng đã tồn tại", BAD_REQUEST);
            }
            user.setUsername(updateUserDto.getUsername());
        }

        if (updateUserDto.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(CharBuffer.wrap(updateUserDto.getPassword())));
            Arrays.fill(updateUserDto.getPassword(), '\0'); // Xóa mật khẩu ra khỏi bộ nhớ sau khi đã mã hóa
        }

        user.setEmail(updateUserDto.getEmail());
        user.setFirstName(updateUserDto.getFirstName());
        user.setLastName(updateUserDto.getLastName());
        user.setPhoneNumber(updateUserDto.getPhoneNumber());
        user.setUserType(updateUserDto.getUserType());
        user.setProfilePicture(updateUserDto.getProfilePicture());

        User updateUser = userRepository.save(user);
        log.info("Cập nhật thông tin người dùng với ID: {} thành công", updateUser.getUserID());
        return userMapper.toUserDto(updateUser);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return userList.stream().map(user -> modelMapper.map(user,UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public UserDto getUserByUsername(String username) {
        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new AppException("User not found", NOT_FOUND));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto findByLogin(String login) {
        User user = userRepository.findUserByUsername(login)
                .orElseThrow(() -> new AppException("user not found", NOT_FOUND));
        return userMapper.toUserDto(user);
    }


    @Override
    public void deleteUser(Long id) {
        log.info("Đang cố gắng xóa người dùng với ID: {}", id);
        if (!userRepository.existsById(id)) {
            throw new AppException("Người dùng không tồn tại", NOT_FOUND);
        }
        userRepository.deleteById(id);
        log.info("Người dùng với ID: {} đã được xóa thành công", id);
    }
}
