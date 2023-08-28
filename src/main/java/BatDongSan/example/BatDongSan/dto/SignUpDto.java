package BatDongSan.example.BatDongSan.dto;

import BatDongSan.example.BatDongSan.entity.Property;
import BatDongSan.example.BatDongSan.entity.UserType;

import java.util.Set;

public record SignUpDto(String username, char[] password, String email, String firstName, String lastName, String phoneNumber, UserType userType, String profilePicture, Set<Property> propertySet) {
}
