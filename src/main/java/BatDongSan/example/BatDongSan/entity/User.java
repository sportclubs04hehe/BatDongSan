package BatDongSan.example.BatDongSan.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID; // Mã định danh duy nhất cho mỗi người dùng

    @NotBlank(message = "username không được để trống")
    @Size(max = 50)
    @Column(unique = true)
    private String username; // Tên duy nhất để xác thực người dùng

    @NotBlank(message = "password không đuợc để trống")
    @Size(max = 120)
    @JsonIgnore // Để không để lộ mật khẩu trong phản hồi API
    private String password; // Chuỗi băm để bảo mật trong quá trình xác thực.

    @NotBlank
    @Email(message = "Email không hợp lệ")
    @Column(unique = true)
    private String email; // Thông tin liên hệ, định dạng bắt buộc chuẩn email.

    @NotBlank(message = "firstName không được để trống")
    private String firstName; // tên người dùng

    @NotBlank
    private String lastName; // tên người dùng
    @Pattern(regexp = "^(\\+84|0)[3-9][0-9]{8}$", message = "Số điện thoại không đúng định dạng")
    private String phoneNumber; // Thông tin liên lạc, regexp phone number Viet Nam

    @Enumerated(EnumType.STRING)
    private UserType userType; // Xác định xem người dùng là đại lý, người mua hay người bán.

    @Enumerated(EnumType.STRING)
    private Role role;

    private String profilePicture; // Ảnh đại diện

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Property> properties;

    public User(String username, String password, String email, String firstName, String lastName, String phoneNumber, UserType userType, String profilePicture, Set<Property> properties) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.userType = userType;
        this.profilePicture = profilePicture;
        this.properties = properties;
    }
}
