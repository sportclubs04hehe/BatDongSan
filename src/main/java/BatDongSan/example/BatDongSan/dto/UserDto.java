package BatDongSan.example.BatDongSan.dto;

import BatDongSan.example.BatDongSan.entity.Property;
import BatDongSan.example.BatDongSan.entity.Role;
import BatDongSan.example.BatDongSan.entity.UserType;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long userID;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String phoneNumber;
    private UserType userType;
    private String profilePicture;
}
