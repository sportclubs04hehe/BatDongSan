package BatDongSan.example.BatDongSan.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "article_pricing")
@Getter @Setter @NoArgsConstructor
public class ArticlePricing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ArticleType articleType;

    private Double pricePerDay;
    private Integer minimumDays;

    public ArticlePricing(ArticleType articleType, Double pricePerDay, Integer minimumDays) {
        this.articleType = articleType;
        this.pricePerDay = pricePerDay;
        this.minimumDays = minimumDays;
    }
}
