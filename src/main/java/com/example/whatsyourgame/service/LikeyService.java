package com.example.whatsyourgame.service;

import com.example.whatsyourgame.entity.Likey;
import com.example.whatsyourgame.entity.Review;
import com.example.whatsyourgame.entity.User;
import com.example.whatsyourgame.repository.LikeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class LikeyService {

    @Autowired
    private LikeyRepository likeyRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ReviewService reviewService;

    // 추천 중복 확인
    public int likeDuplicateCheck(Long reviewId) {
        User user = userService.currentLoginUser().orElse(null);
        Review review = reviewService.findById(reviewId).orElse(null);
        if (user.getId() == review.getUser().getId()) return 2;
        else if (likeyRepository.findLikeyByUserIdAndReviewId(user.getId(), reviewId).isEmpty()) return 1;
        else return 0;
    }

    public Likey like(Long reviewId) {
        int likeCnt = reviewService.findLikeCntByReviewId(reviewId);
        reviewService.updateLikeCnt(reviewId, likeCnt+1);

        Likey likey = new Likey();
        User user = userService.currentLoginUser().orElse(null);
        likey.setUser(user);
        Review review = reviewService.findById(reviewId).orElse(null);
        likey.setReview(review);
        likey.setCreatedAt(LocalDateTime.now());
        likey.setUpdatedAt(LocalDateTime.now());
        return likeyRepository.save(likey);
    }

    public int dislike(Long reviewId) {
        int likeCnt = reviewService.findLikeCntByReviewId(reviewId);
        reviewService.updateLikeCnt(reviewId, likeCnt-1);

        User user = userService.currentLoginUser().orElse(null);
        Likey likey = likeyRepository.findLikeyByUserIdAndReviewId(user.getId(), reviewId).orElse(null);
        return likeyRepository.delete(likey.getId());
    }

}
