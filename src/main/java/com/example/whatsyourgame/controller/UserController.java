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
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final GameService gameService;
    private final ReviewService reviewService;

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

    @ResponseBody
    @PostMapping("emailDuplicateCheck")
    public int emailDuplicateCheck(@RequestParam("email") String email) {
        return userService.emailDuplicateCheck(email);
    }

    @PostMapping("login")
    public String login(User user) {
        if (userService.loginCheck(user)) return "redirect:/";
        else return "login-error";
    }

    @GetMapping("login-error")
    public String loginError(){
        return "login-error";
    }

    @GetMapping("mypage")
    public String mypage(Model model) {
        User user = userService.currentLoginUser()
                .orElseThrow(() -> new IllegalArgumentException("로그인 유저가 존재하지 않습니다."));
        model.addAttribute("user", user);
        List<Game> wishList = gameService.findWishList(user.getId());   // 찜한 게임
        model.addAttribute("wishList", wishList);
        List<Review> reviewList = reviewService.findReviewsByUserId(user.getId());   // 찜한 게임
        model.addAttribute("reviewList", reviewList);
        return "mypage";
    }
}
