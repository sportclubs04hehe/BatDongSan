package BatDongSan.example.BatDongSan.dto;

import BatDongSan.example.BatDongSan.entity.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class UpdateUserDto {
    private String username;
    private String email;
    private char[] password; // Cân nhắc xử lý mật khẩu cẩn thận
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private UserType userType;
    private String profilePicture;
}
