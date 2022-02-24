package com.example.whatsyourgame.controller;

import com.example.whatsyourgame.entity.User;
import com.example.whatsyourgame.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("")
    public String login() {
        return "login-form";
    }

    @PostMapping("")
    public String join(User user) {
        userService.save(user);
        userService.login(user);
        return "redirect:/";
    }

}
