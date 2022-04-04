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

    public List<Review> findReviewsByUserId(Long userId) {
        return reviewRepository.findReviewsByUserId(userId);
    }

    public Review save(Review review) {
        review.setLikeCnt(0);
        review.setCreatedAt(LocalDateTime.now());
        review.setUpdatedAt(LocalDateTime.now());
        return reviewRepository.save(review);
    }

    // 리뷰 작성자 중복 확인
    public boolean reviewDuplicateCheck(Long gameId, Long userId) {
        return reviewRepository.findReviewByGameIdAndUserId(gameId, userId).isEmpty();
    }

    // 리뷰 최근 작성순으로 출력
    public List<Review> findReviewsByGameId(Long gameId) {
        return reviewRepository.findReviewsByGameIdOrderByCreatedAtDesc(gameId);
    }

    public void update(Review review, Review reviewReq) {
        review.update(reviewReq.getScore(), reviewReq.getContent());
    }

    public void delete(Review review) {
        reviewRepository.delete(review);
    }

    public void updateLikeCnt(Review review, int likeCnt) {
        review.updateLikeCnt(review.getLikeCnt() + likeCnt);
    }
}
