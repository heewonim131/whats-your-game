package com.example.whatsyourgame.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameReviewInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int reviewCnt;

    private float avgReviewScore;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @OneToOne(optional = false)
    private Game game;

    public void update(int reviewCnt, int reviewScore) {
        this.reviewCnt = reviewCnt;
        this.avgReviewScore = (this.avgReviewScore * this.reviewCnt + reviewScore) / (float) reviewCnt;
        this.updatedAt = LocalDateTime.now();
    }
}
