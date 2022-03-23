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
                .orElseThrow(() -> new IllegalArgumentException("해당 게임 정보가 존재하지 않습니다. id="+gameId));
        gameReviewInfoService.update(gameId, gameReviewInfo.getReviewCnt() + 1, review.getScore());
    }

    @ResponseBody
    @PostMapping("reviewDuplicateCheck")
    public int reviewDuplicateCheck(@RequestParam("gameId") Long gameId) {
        User user = userService.currentLoginUser()
                .orElseThrow(() -> new IllegalArgumentException("로그인 유저가 존재하지 않습니다."));
        return reviewService.reviewDuplicateCheck(gameId, user.getId());
    }

    @ResponseBody
    @PutMapping("{reviewId}")
    public void update(@PathVariable Long reviewId, Review reviewReq) {
        reviewService.update(reviewId, reviewReq);
    }

    @ResponseBody
    @DeleteMapping("{reviewId}")
    public void delete(@PathVariable Long reviewId, @RequestParam("gameId") Long gameId) {
        Review review = reviewService.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("해당 리뷰가 존재하지 않습니다. id="+reviewId));
        reviewService.delete(reviewId);
        GameReviewInfo gameReviewInfo = gameReviewInfoService.findGameReviewInfoByGameId(gameId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게임 정보가 존재하지 않습니다. id="+gameId));
        gameReviewInfoService.update(gameId, gameReviewInfo.getReviewCnt() - 1, review.getScore() * (-1));
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
        return likeyService.likeDuplicateCheck(reviewId);
    }

    @ResponseBody
    @PostMapping("{reviewId}/like")
    public void like(@PathVariable Long reviewId) {
        likeyService.like(reviewId);
    }

    @ResponseBody
    @DeleteMapping("{reviewId}/dislike")
    public void dislike(@PathVariable Long reviewId) {
        likeyService.dislike(reviewId);
    }

    @ResponseBody
    @PostMapping("{reviewId}/likeCnt")
    public int likeCnt(@PathVariable Long reviewId) {
        return reviewService.findById(reviewId).get().getLikeCnt();
    }

}
