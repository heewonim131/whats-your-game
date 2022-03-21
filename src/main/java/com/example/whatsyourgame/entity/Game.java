package com.example.whatsyourgame.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String description;

    @NonNull
    private String img;

    @NonNull
    private String genre;

    @NonNull
    private String platform;

    @NonNull
    private String site;

    @NonNull
    private LocalDate releasedAt;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @OneToMany
    @JoinColumn(name = "game_id")
    @ToString.Exclude
    private List<Review> reviews = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "game_id")
    @ToString.Exclude
    private List<Wish> wishs = new ArrayList<>();
}
