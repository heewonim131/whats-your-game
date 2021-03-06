package com.example.whatsyourgame.controller;

import com.example.whatsyourgame.entity.Game;
import com.example.whatsyourgame.entity.GameReviewInfo;
import com.example.whatsyourgame.entity.Review;
import com.example.whatsyourgame.entity.User;
import com.example.whatsyourgame.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final UserService userService;
    private final GameService gameService;
    private final ReviewService reviewService;
    private final LikeyService likeyService;
    private final GameReviewInfoService gameReviewInfoService;

    @ResponseBody
    @PostMapping("reviewDuplicateCheck")
    public boolean reviewDuplicateCheck(@RequestParam("gameId") Long gameId) {
        User user = userService.currentLoginUser()
                .orElseThrow(() -> new IllegalArgumentException("로그인 유저가 존재하지 않습니다."));
        return reviewService.reviewDuplicateCheck(gameId, user.getId());
    }

    @ResponseBody
    @PostMapping("")
    public void write(@RequestParam("gameId") Long gameId, Review review) {
        User user = userService.currentLoginUser()
                .orElseThrow(() -> new IllegalArgumentException("로그인 유저가 존재하지 않습니다."));
        review.setUser(user);
        Game game = gameService.findById(gameId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게임이 존재하지 않습니다. id="+gameId));
        review.setGame(game);
        reviewService.save(review);
        GameReviewInfo gameReviewInfo = gameReviewInfoService.findGameReviewInfoByGameId(gameId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게임 정보가 존재하지 않습니다. id=" + gameId));
        gameReviewInfoService.update(gameReviewInfo, 1, review.getScore());
    }

    // 수정삭제 권한 확인
    @ResponseBody
    @GetMapping("{reviewId}/checkReviewer")
    public boolean checkReviewer(@PathVariable Long reviewId) {
        User user = userService.currentLoginUser()
                .orElseThrow(() -> new IllegalArgumentException("로그인 유저가 존재하지 않습니다."));
        Review review = reviewService.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("해당 리뷰가 존재하지 않습니다. id=" + reviewId));
        return user.getId().equals(review.getUser().getId());

    }

    @ResponseBody
    @PutMapping("{reviewId}")
    public void update(@PathVariable Long reviewId, Review reviewReq, @RequestParam("gameId") Long gameId) {
        Review review = reviewService.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("해당 리뷰가 존재하지 않습니다. id="+reviewId));
        GameReviewInfo gameReviewInfo = gameReviewInfoService.findGameReviewInfoByGameId(gameId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게임 정보가 존재하지 않습니다. id=" + gameId));
        gameReviewInfoService.update(gameReviewInfo, 0, reviewReq.getScore()-review.getScore());
        reviewService.update(review, reviewReq);
    }

    @ResponseBody
    @DeleteMapping("{reviewId}")
    public void delete(@PathVariable Long reviewId, @RequestParam("gameId") Long gameId) {
        Review review = reviewService.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("해당 리뷰가 존재하지 않습니다. id="+reviewId));
        GameReviewInfo gameReviewInfo = gameReviewInfoService.findGameReviewInfoByGameId(gameId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게임 정보가 존재하지 않습니다. id=" + gameId));
        gameReviewInfoService.update(gameReviewInfo, -1, review.getScore() * (-1));
        reviewService.delete(review);
    }

    @ResponseBody
    @PostMapping("likeLoginCheck")
    public int likeLoginCheck() {
        if (userService.currentLoginUser().isPresent()) return 1;
        else return 0;
    }

    @ResponseBody
    @PostMapping("{reviewId}/likeDuplicateCheck")
    public int likeDuplicateCheck(@PathVariable Long reviewId) {
        User user = userService.currentLoginUser()
                .orElseThrow(() -> new IllegalArgumentException("로그인 유저가 존재하지 않습니다."));
        Review review = reviewService.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("해당 리뷰가 존재하지 않습니다. id=" + reviewId));
        return likeyService.likeDuplicateCheck(user, review);
    }

    @ResponseBody
    @PostMapping("{reviewId}/like")
    public void like(@PathVariable Long reviewId) {
        User user = userService.currentLoginUser()
                .orElseThrow(() -> new IllegalArgumentException("로그인 유저가 존재하지 않습니다."));
        Review review = reviewService.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("해당 리뷰가 존재하지 않습니다. id="+reviewId));
        likeyService.like(user, review);
        reviewService.updateLikeCnt(review, 1);
    }

    @ResponseBody
    @DeleteMapping("{reviewId}/dislike")
    public void dislike(@PathVariable Long reviewId) {
        User user = userService.currentLoginUser()
                .orElseThrow(() -> new IllegalArgumentException("로그인 유저가 존재하지 않습니다."));
        Review review = reviewService.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("해당 리뷰가 존재하지 않습니다. id="+reviewId));
        likeyService.dislike(user, review);
        reviewService.updateLikeCnt(review, -1);
    }

    @ResponseBody
    @GetMapping("{reviewId}/likeCnt")
    public int likeCnt(@PathVariable Long reviewId) {
        Review review = reviewService.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("해당 리뷰가 존재하지 않습니다. id="+reviewId));
        return review.getLikeCnt();
    }

}
