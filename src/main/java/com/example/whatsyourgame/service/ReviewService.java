package com.example.whatsyourgame.service;

import com.example.whatsyourgame.entity.Review;
import com.example.whatsyourgame.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public Optional<Review> findById(Long reviewId) {
        return reviewRepository.findById(reviewId);
    }

    public Review save(Review review) {
        review.setLikeCnt(0);
        review.setCreatedAt(LocalDateTime.now());
        review.setUpdatedAt(LocalDateTime.now());
        return reviewRepository.save(review);
    }

    // 리뷰 작성자 중복 확인
    public int reviewDuplicateCheck(Long gameId, Long userId) {
        if (reviewRepository.findReviewByGameIdAndUserId(gameId, userId).isEmpty()) return 1;
        else return 0;
    }

    // 리뷰 최근 작성순으로 출력
    public List<Review> findReviewsByGameId(Long gameId) {
        return reviewRepository.findReviewsByGameIdOrderByCreatedAtDesc(gameId);
    }

    public Long update(Long reviewId, Review reviewReq) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("해당 리뷰가 존재하지 않습니다. id=" + reviewId));
        review.update(reviewReq.getScore(), reviewReq.getContent());
        return reviewId;
    }

    public void delete(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("해당 리뷰가 존재하지 않습니다. id=" + reviewId));
        reviewRepository.delete(review);
    }

    public Long like(Long reviewId, int likeCnt) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("해당 리뷰가 존재하지 않습니다. id=" + reviewId));
        review.like(likeCnt);
        return reviewId;
    }
}
