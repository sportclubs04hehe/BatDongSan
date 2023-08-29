package BatDongSan.example.BatDongSan.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "properties")
@Getter
@Setter
@NoArgsConstructor
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long propertyID; // Mã định danh duy nhất cho mỗi thuộc tính

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ownerID", referencedColumnName = "userID")
    private User owner; //  Kết nối thuộc tính với một người dùng cụ thể

    @NotNull
    private String address; // Vị trí bất động sản
    @NotNull
    private String city; // Vị trí bất động sản

    @NotNull
    private String zipCode; // Vị trí bất động sản

    private Double latitude; // Làm việc với dịch vụ bản đồ
    private Double longitude; // Làm việc với dịch vụ bản đồ

    @NotNull
    private BigDecimal price; // Giá trị kinh tế hoặc giá niêm yết của tài sản.
    @NotNull
    private String province; // tỉnh
    @NotNull
    private String district; // huyện
    @NotNull
    private String commune; // xã
    private String street; // đường phố ( ví dụ: Hoàng Hoa Thám)
    private String project; // tên dự án
    private String displayedAddress; // địa chỉ được hiển thị

    @Enumerated(EnumType.STRING)
    @NotNull
    private PropertyType propertyType; // Loại tài sản (ví dụ: nhà, căn hộ)

    @Enumerated(EnumType.STRING)
    private ArticleType articleType;

    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull
    private Integer bedrooms; // Chi tiết tài sản.

    @NotNull
    private Integer bathrooms; // Chi tiết tài sản.

    @NotNull
    private Double squareFootage; // Chi tiết tài sản.

    @Column(columnDefinition = "TEXT")
    private String description; // Thông tin bổ sung về tài sản

    @Enumerated(EnumType.STRING)
    private PropertyStatus propertyStatus; // Tình trạng hiện tại của bất động sản trên thị trường

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @OneToMany(mappedBy = "property", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<PropertyImage> images;

    @OneToMany(mappedBy = "property", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Comment> comments;

    @OneToMany(mappedBy = "property", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Favorite> favorites;

    @OneToMany(mappedBy = "property", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Message> messages;

}
