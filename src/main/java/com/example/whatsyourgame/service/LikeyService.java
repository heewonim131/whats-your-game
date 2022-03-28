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

    // 추천 중복 확인
    public int likeDuplicateCheck(User user, Review review) {
        if (user.getId().equals(review.getUser().getId())) return 2;
        else if (likeyRepository.findLikeyByUserIdAndReviewId(user.getId(), review.getId()).isEmpty()) return 1;
        else return 0;
    }

    public Likey like(User user, Review review) {
        Likey likey = new Likey();
        likey.setUser(user);
        likey.setReview(review);
        likey.setCreatedAt(LocalDateTime.now());
        likey.setUpdatedAt(LocalDateTime.now());
        return likeyRepository.save(likey);
    }

    public void dislike(User user, Review review) {
        Likey likey = likeyRepository.findLikeyByUserIdAndReviewId(user.getId(), review.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 추천이 존재하지 않습니다."));
        likeyRepository.delete(likey);
    }

}
