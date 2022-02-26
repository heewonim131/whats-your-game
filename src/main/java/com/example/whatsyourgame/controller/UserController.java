package com.example.whatsyourgame.controller;

import com.example.whatsyourgame.entity.User;
import com.example.whatsyourgame.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final HttpSession httpSession;
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

    @ResponseBody
    @PostMapping("emailDuplicateCheck")
    public int emailDuplicateCheck(@RequestParam("email") String email) {
        if (userService.findUser(email).isEmpty()) return 1;
        else return 0;
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
    public String mypage(Principal principal, Model model) {
        User user = (User) httpSession.getAttribute("user");
        if (user != null) {
            model.addAttribute("userName", user.getName());
            return "mypage";
        }
        else if (principal != null) {
            model.addAttribute("userName", principal.getName());
            return "mypage";
        }
        else return "login-form";
    }

}
