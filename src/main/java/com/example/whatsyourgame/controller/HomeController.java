package com.example.whatsyourgame.controller;

import com.example.whatsyourgame.entity.Game;
import com.example.whatsyourgame.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final GameService gameService;

    @GetMapping("/")
    public String main(Model model) {
        List<Game> latestGameList = gameService.findTop4GamesByOrderByReleasedAtDesc(); // 신작게임
        model.addAttribute("latestGameList", latestGameList);
        List<Game> popularGameList = gameService.findTop6GamesByOrderByIdAsc(); // 인기게임
        model.addAttribute("popularGameList", popularGameList);
        return "index";
    }
}

