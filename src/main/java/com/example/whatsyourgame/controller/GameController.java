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

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/games")
public class GameController {

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
    public String gameDetails(@PathVariable(name = "gameId") Long gameId, Model model) {
        Game game = gameService.findById(gameId).get();
        model.addAttribute("game", game);
        List<Review> reviews = reviewService.findReviewsByGameId(gameId);
        model.addAttribute("reviews", reviews);
        return "game-details";
    }

}
