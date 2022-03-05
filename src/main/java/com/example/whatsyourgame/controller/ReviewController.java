package com.example.whatsyourgame.controller;

import com.example.whatsyourgame.entity.Game;
import com.example.whatsyourgame.entity.Review;
import com.example.whatsyourgame.entity.User;
import com.example.whatsyourgame.service.GameService;
import com.example.whatsyourgame.service.LikeyService;
import com.example.whatsyourgame.service.ReviewService;
import com.example.whatsyourgame.service.UserService;
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
    public void delete(@PathVariable Long reviewId) {
        reviewService.delete(reviewId);
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
