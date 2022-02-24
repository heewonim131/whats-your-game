package com.example.whatsyourgame.controller;

import com.example.whatsyourgame.entity.User;
import com.example.whatsyourgame.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final HttpSession httpSession;
    private final UserService userService;

    @GetMapping("/")
    public String main(Model model) {
        User user = (User) httpSession.getAttribute("user");
        if (user != null) {
            model.addAttribute("userId", user.getId());
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }
}

