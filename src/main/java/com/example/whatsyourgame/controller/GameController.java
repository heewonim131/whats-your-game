package com.example.whatsyourgame.controller;

import com.example.whatsyourgame.entity.Game;
import com.example.whatsyourgame.entity.Review;
import com.example.whatsyourgame.entity.User;
import com.example.whatsyourgame.service.GameService;
import com.example.whatsyourgame.service.ReviewService;
import com.example.whatsyourgame.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/games")
public class GameController {

    private final UserService userService;
    private final GameService gameService;
    private final ReviewService reviewService;

    @GetMapping("")
//    정렬기준 기본, popular, latest
    public String gameList(Model model) {
        List<Game> gameList = gameService.findAll();
        model.addAttribute("gameList", gameList);
        return "game-list";
    }

    @GetMapping("{gameId}")
    public String gameDetails(@PathVariable Long gameId, Model model) {
        if (userService.currentLoginUser().isPresent()) {
            User user = userService.currentLoginUser().orElse(null);
            model.addAttribute("userId", user.getId());
        } else {
            model.addAttribute("userId", 0);
        }
        Game game = gameService.findById(gameId).orElse(null);
        model.addAttribute("game", game);
        List<Review> reviews = reviewService.findReviewsByGameId(gameId);
        model.addAttribute("reviews", reviews);
        return "game-details";
    }

}
