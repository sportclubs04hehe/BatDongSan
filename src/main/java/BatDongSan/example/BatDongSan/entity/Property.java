package BatDongSan.example.BatDongSan.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
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
    private String state; // Vị trí bất động sản

    @NotNull
    private String zipCode; // Vị trí bất động sản

    @NotNull
    private Double price; // Giá trị kinh tế hoặc giá niêm yết của tài sản.

    @Enumerated(EnumType.STRING)
    @NotNull
    private PropertyType propertyType; // Loại tài sản (ví dụ: nhà, căn hộ)

    @NotNull
    private Integer bedrooms; // Chi tiết tài sản.

    @NotNull
    private Integer bathrooms; // Chi tiết tài sản.

    @NotNull
    private Double squareFootage; // Chi tiết tài sản.

    @Column(columnDefinition = "TEXT")
    private String description; // Thông tin bổ sung về tài sản

    @Enumerated(EnumType.STRING)
    private PropertyStatus status; // Tình trạng hiện tại của bất động sản trên thị trường

    @Temporal(TemporalType.DATE)
    private Date dateListed; // Khi tài sản được cung cấp trên nền tảng (ngày niêm yết lên web)

    @OneToMany(mappedBy = "property", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<PropertyImage> images;

    @OneToMany(mappedBy = "property", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Comment> comments;

    @OneToMany(mappedBy = "property", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Favorite> favorites;

    @OneToMany(mappedBy = "property", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Message> messages;

    public Property(User owner, String address, String city, String state, String zipCode, Double price, PropertyType propertyType, Integer bedrooms, Integer bathrooms, Double squareFootage, String description, PropertyStatus status, Date dateListed, Set<PropertyImage> images, Set<Comment> comments, Set<Favorite> favorites, Set<Message> messages) {
        this.owner = owner;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.price = price;
        this.propertyType = propertyType;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.squareFootage = squareFootage;
        this.description = description;
        this.status = status;
        this.dateListed = dateListed;
        this.images = images;
        this.comments = comments;
        this.favorites = favorites;
        this.messages = messages;
    }
}
