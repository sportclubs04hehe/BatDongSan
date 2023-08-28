package BatDongSan.example.BatDongSan.dto;

import BatDongSan.example.BatDongSan.entity.PropertyStatus;
import BatDongSan.example.BatDongSan.entity.PropertyType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PropertyDTO {
    private Long propertyID;
    private UserDto owner;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private Double price;
    private PropertyType propertyType;
    private Integer bedrooms;
    private Integer bathrooms;
    private Double squareFootage;
    private String description;
    private PropertyStatus status;
    private Date dateListed;
}
