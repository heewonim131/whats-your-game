package com.example.whatsyourgame.controller;

import com.example.whatsyourgame.entity.Game;
import com.example.whatsyourgame.service.GameService;
import com.example.whatsyourgame.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final GameService gameService;

    @GetMapping("/")
    public String main(Model model) {
        // 신작게임 4개
        List<Game> latestGameList = gameService.findLatestTop4();
        model.addAttribute("latestGameList", latestGameList);

        // 인기게임 6개
        // 별점을 나타낼 GameReviewInfo 와 조인
        List<Game> popularGameList = gameService.findPopularTop6();
        model.addAttribute("popularGameList", popularGameList);

        return "index";
    }
}

