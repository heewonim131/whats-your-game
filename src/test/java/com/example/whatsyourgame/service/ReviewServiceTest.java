package com.example.whatsyourgame.service;

import com.example.whatsyourgame.entity.Game;
import com.example.whatsyourgame.entity.Review;
import com.example.whatsyourgame.entity.User;
import com.example.whatsyourgame.repository.GameRepository;
import com.example.whatsyourgame.repository.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReviewServiceTest {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    void save() {
        Review review = new Review();
        review.setScore(5);
        review.setContent("리뷰 등록");
        review.setUser(new User());
        review.setGame(new Game());
        reviewService.save(review);

        List<Review> list = reviewRepository.findAll();
        assertEquals(1, list.size());
    }

    @Test
    void update() {
        Review review = new Review();
        review.setScore(5);
        review.setContent("리뷰 등록");
        review.setUser(new User());
        review.setGame(new Game());
        reviewService.save(review);

        review.setScore(1);
        review.setContent("리뷰 수정");
        reviewService.update(review);

        Review result = reviewRepository.findById(review.getId()).orElse(null);
        assertEquals(1, result.getScore());
        assertEquals("리뷰 수정", result.getContent());
    }

    @Test
    void delete() {
        Review review = new Review();
        review.setScore(5);
        review.setContent("리뷰 등록");
        review.setUser(new User());
        review.setGame(new Game());
        reviewService.save(review);

        reviewService.delete(review.getId());
        List<Review> list = reviewRepository.findAll();
        assertEquals(0, list.size());
    }

}
