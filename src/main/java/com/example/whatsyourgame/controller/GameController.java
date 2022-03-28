package com.example.whatsyourgame.controller;

import com.example.whatsyourgame.entity.Game;
import com.example.whatsyourgame.entity.Review;
import com.example.whatsyourgame.entity.User;
import com.example.whatsyourgame.entity.Wish;
import com.example.whatsyourgame.service.GameService;
import com.example.whatsyourgame.service.ReviewService;
import com.example.whatsyourgame.service.UserService;
import com.example.whatsyourgame.service.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/games")
public class GameController {

    private final UserService userService;
    private final GameService gameService;
    private final ReviewService reviewService;
    private final WishService wishService;

    @GetMapping("")
    public String gameList(Model model, Pageable pageable) {
        Page<Game> gameList = gameService.findAll(pageable);
        model.addAttribute("gameList", gameList);
        return "game-list";
    }

    @PostMapping("")
    public String searchGames(@RequestParam("search") String search, Model model, Pageable pageable) {
        Page<Game> gameList = gameService.findGamesByNameContaining(search, pageable);
        model.addAttribute("search", search);
        model.addAttribute("gameList", gameList);
        return "game-list";
    }

    @GetMapping("{gameId}")
    public String gameDetails(@PathVariable Long gameId, Model model) {
        Optional<User> user = userService.currentLoginUser();
        model.addAttribute("user", user.orElse(null));
        Game game = gameService.findById(gameId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게임이 존재하지 않습니다. id="+gameId));
        model.addAttribute("game", game);
        List<Review> reviewList = reviewService.findReviewsByGameId(gameId);
        model.addAttribute("reviewList", reviewList);
        Boolean isWished = false;
        if (user.isPresent()) isWished = wishService.findWishByUserIdAndGameId(user.get().getId(), gameId).isPresent();
        model.addAttribute("isWished", isWished);
        return "game-details";
    }

    @ResponseBody
    @PostMapping("wishLoginCheck")
    public int wishLoginCheck() {
        if (userService.currentLoginUser().isPresent()) return 1;
        else return 0;
    }

    @ResponseBody
    @PostMapping("{gameId}/wish")
    public void wish(@PathVariable Long gameId) {
        User user = userService.currentLoginUser()
                .orElseThrow(() -> new IllegalArgumentException("로그인 유저가 존재하지 않습니다."));
        Game game = gameService.findById(gameId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게임이 존재하지 않습니다. id="+gameId));
        wishService.wish(user, game);
    }

    @ResponseBody
    @DeleteMapping("{gameId}/wish")
    public void deleteWish(@PathVariable Long gameId) {
        User user = userService.currentLoginUser()
                .orElseThrow(() -> new IllegalArgumentException("로그인 유저가 존재하지 않습니다."));
        Wish wish = wishService.findWishByUserIdAndGameId(user.getId(), gameId)
                .orElseThrow(() -> new IllegalArgumentException("해당 찜이 존재하지 않습니다. id="+gameId));
        wishService.deleteWish(wish);
    }

}
