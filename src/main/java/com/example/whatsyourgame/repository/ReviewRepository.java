package com.example.whatsyourgame.repository;

import com.example.whatsyourgame.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findReviewsByGameIdOrderByCreatedAtDesc(Long gameId);

    Optional<Review> findReviewByGameIdAndUserId(Long gameId, Long userId);

    @Modifying(clearAutomatically = true)
    @Query(value = "update review r set r.score = :score, r.content = :content, r.updated_at = :updated_at where r.id = :reviewId", nativeQuery = true)
    int update(Long reviewId, int score, String content, LocalDateTime updated_at);

    @Modifying(clearAutomatically = true)
    @Query(value = "delete from review r where r.id = :reviewId", nativeQuery = true)
    int delete(Long reviewId);

    @Query(value = "select r.like_cnt from review r where r.id = :reviewId", nativeQuery = true)
    int findLikeCntByReviewId(Long reviewId);

    @Modifying(clearAutomatically = true)
    @Query(value = "update review r set r.like_cnt = :like_cnt where r.id = :reviewId", nativeQuery = true)
    int updateLikeCnt(Long reviewId, int like_cnt);

}
