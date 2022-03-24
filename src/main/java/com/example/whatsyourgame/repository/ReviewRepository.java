package com.example.whatsyourgame.repository;

import com.example.whatsyourgame.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findReviewsByUserId(Long userId);
    List<Review> findReviewsByGameIdOrderByCreatedAtDesc(Long gameId);
    Optional<Review> findReviewByGameIdAndUserId(Long gameId, Long userId);
}
