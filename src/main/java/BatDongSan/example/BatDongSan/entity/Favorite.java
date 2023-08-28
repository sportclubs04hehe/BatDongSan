package BatDongSan.example.BatDongSan.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "favorites")
@Getter
@Setter
@NoArgsConstructor
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long favoriteID; // Mã định danh duy nhất cho hành động yêu thích.

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    private User user; // Ai đã đánh dấu thuộc tính là yêu thích.

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "propertyID", referencedColumnName = "propertyID")
    private Property property; // Thuộc tính nào được đánh dấu là yêu thích.

    public Favorite(User user, Property property) {
        this.user = user;
        this.property = property;
    }
}
