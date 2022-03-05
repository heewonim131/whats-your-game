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
        User user = userService.currentLoginUser()
                .orElseThrow(() -> new IllegalArgumentException("로그인 유저가 존재하지 않습니다."));
        Review review = reviewService.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("해당 리뷰가 존재하지 않습니다. id=" + reviewId));
        if (user.getId() == review.getUser().getId()) return 2;
        else if (likeyRepository.findLikeyByUserIdAndReviewId(user.getId(), reviewId).isEmpty()) return 1;
        else return 0;
    }

    public Likey like(Long reviewId) {
        Review review = reviewService.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("해당 리뷰가 존재하지 않습니다. id=" + reviewId));
        reviewService.like(reviewId, review.getLikeCnt()+1);

        Likey likey = new Likey();
        User user = userService.currentLoginUser().orElse(null);
        likey.setUser(user);
        likey.setReview(review);
        likey.setCreatedAt(LocalDateTime.now());
        likey.setUpdatedAt(LocalDateTime.now());
        return likeyRepository.save(likey);
    }

    public void dislike(Long reviewId) {
        Review review = reviewService.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("해당 리뷰가 존재하지 않습니다. id=" + reviewId));
        reviewService.like(reviewId, review.getLikeCnt()-1);

        User user = userService.currentLoginUser().orElse(null);
        Likey likey = likeyRepository.findLikeyByUserIdAndReviewId(user.getId(), reviewId)
                .orElseThrow(() -> new IllegalArgumentException("해당 추천이 존재하지 않습니다."));
        likeyRepository.delete(likey);
    }

}
