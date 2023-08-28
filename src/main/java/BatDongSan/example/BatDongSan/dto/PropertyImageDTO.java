package BatDongSan.example.BatDongSan.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PropertyImageDTO {
    private Long imageID;
    private Long propertyID; // Chỉ tham chiếu ID thay vì toàn bộ PropertyDTO
    private String imageURL;
}
