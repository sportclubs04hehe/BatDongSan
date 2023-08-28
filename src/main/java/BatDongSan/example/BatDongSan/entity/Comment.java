package BatDongSan.example.BatDongSan.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "comments")
@Getter
@Setter
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "propertyID", referencedColumnName = "propertyID")
    private Property property; // Thuộc tính nào đang được bình luận

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    private User user; // Ai đã viết bình luận.

    private String text; //  Nội dung bình luận.

    @Temporal(TemporalType.TIMESTAMP)
    private Date datePosted; // Khi bình luận được thực hiện ( ngày bình luận đăng)

    public Comment(Property property, User user, String text, Date datePosted) {
        this.property = property;
        this.user = user;
        this.text = text;
        this.datePosted = datePosted;
    }
}
