package com.example.whatsyourgame.service;

import com.example.whatsyourgame.entity.User;
import com.example.whatsyourgame.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HttpSession httpSession;

    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException(email));
    }

    public User save(User user) {
        user.setPassword(getPasswordEncoder().encode(user.getPassword()));
        user.setRole("ROLE_USER");
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    // 이메일 중복 확인
    public int emailDuplicateCheck(String email) {
        if (userRepository.findByEmail(email).isEmpty()) return 1;
        else return 0;
    }

    // 회원가입 후 자동 로그인
    public void login(User user) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                user.getName(), user.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_USER")));
        SecurityContextHolder.getContext().setAuthentication(token);
    }

    public Optional<User> findUser(String email) {
        return userRepository.findByEmail(email);
    }

    // 로그인 시 암호화된 비밀번호 비교
    public boolean loginCheck(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            User actUser = userRepository.findByEmail(user.getEmail()).get();
            return getPasswordEncoder().matches(
                    getPasswordEncoder().encode(user.getPassword()),
                    actUser.getPassword());
        } else return false;
    }

    // 현재 로그인 유저 넘기기
    public Optional<User> currentLoginUser() {
        User user = (User) httpSession.getAttribute("user");
        if (user != null) return Optional.of(user);
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            System.out.println("principal instanceof UserDetails");
            return (Optional<User>) principal;
        }
        return Optional.empty();
    }
}
