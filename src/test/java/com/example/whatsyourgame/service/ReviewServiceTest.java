package com.example.whatsyourgame.service;

import com.example.whatsyourgame.entity.Game;
import com.example.whatsyourgame.entity.Review;
import com.example.whatsyourgame.entity.User;
import com.example.whatsyourgame.repository.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {

    @InjectMocks
    private ReviewService reviewService;

    @Mock
    private ReviewRepository reviewRepository;

    @Test
    void save() {
        Review review = new Review();
        review.setScore(5);
        review.setContent("리뷰 등록");
        review.setUser(new User());
        review.setGame(new Game());

        when(this.reviewRepository.save(any(Review.class)))
                .then(AdditionalAnswers.returnsFirstArg());

        Review result = this.reviewService.save(review);

        assertEquals(5, result.getScore());
        assertEquals("리뷰 등록", result.getContent());
    }

    @Test
    void update() {
        Review review = new Review();
        review.setScore(5);
        review.setContent("리뷰 등록");
        review.setUser(new User());
        review.setGame(new Game());
        this.reviewService.save(review);

        Review reviewReq = new Review();
        reviewReq.setScore(1);
        reviewReq.setContent("리뷰 수정");
        this.reviewService.update(review, reviewReq);

        assertEquals(1, review.getScore());
        assertEquals("리뷰 수정", review.getContent());
    }

    @Test
    void delete() {
        Review review = new Review();
        review.setScore(5);
        review.setContent("리뷰 등록");
        review.setUser(new User());
        review.setGame(new Game());
        this.reviewService.save(review);

        this.reviewService.delete(review);
        List<Review> list = reviewRepository.findAll();
        assertEquals(0, list.size());
    }

}
