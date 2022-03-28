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
@ToString(callSuper = true)
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int score;

    private String content;

    private int likeCnt;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Game game;

    @OneToMany
    @JoinColumn(name = "review_id")
    @ToString.Exclude
    @Builder.Default
    private List<Likey> likeys = new ArrayList<>();

    public void update(int score, String content) {
        this.score = score;
        this.content = content;
        this.updatedAt = LocalDateTime.now();
    }

    public void updateLikeCnt(int likeCnt) {
        this.likeCnt = likeCnt;
    }
}
