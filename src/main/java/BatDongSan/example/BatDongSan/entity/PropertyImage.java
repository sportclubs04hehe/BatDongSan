package BatDongSan.example.BatDongSan.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "property_images")
@Getter
@Setter
@NoArgsConstructor
public class PropertyImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageID; // Mã định danh duy nhất cho mỗi hình ảnh

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "propertyID", referencedColumnName = "propertyID")
    private Property property; // Kết nối hình ảnh với một thuộc tính cụ thể.

    private String imageURL; //  Liên kết tới hình ảnh được lưu trữ.

    public PropertyImage(Property property, String imageURL) {
        this.property = property;
        this.imageURL = imageURL;
    }
}
