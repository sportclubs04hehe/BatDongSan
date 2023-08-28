package BatDongSan.example.BatDongSan.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "agents")
@Getter
@Setter
@NoArgsConstructor
public class Agent {
    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agentID", referencedColumnName = "userID")
    private User user; // Liên quan trực tiếp đến ID người dùng.
    private String bio; //  Mô tả ngắn gọn chuyên môn của đại lý.
    private String agencyName; //  Hiệp hội công ty/đại lý.
    private Integer yearsExperience; //  Kinh nghiệm chuyên môn, thường được người mua/người bán sử dụng để đánh giá độ tin cậy.
}
