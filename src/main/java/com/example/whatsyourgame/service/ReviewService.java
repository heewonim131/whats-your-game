package com.example.whatsyourgame.service;

import com.example.whatsyourgame.entity.Review;
import com.example.whatsyourgame.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public Review save(Review review) {
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

}
