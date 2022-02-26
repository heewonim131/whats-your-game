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
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
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
        user.setRole("USER");
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    // 회원가입 후 자동 로그인
    public void login(User user) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                user.getName(), user.getPassword(),
                List.of(new SimpleGrantedAuthority("USER")));
        SecurityContextHolder.getContext().setAuthentication(token);
    }

    public Optional<User> findUser(String email) {
        return userRepository.findByEmail(email);
    }

    // 로그인 시 암호화된 비밀번호 비교
    public boolean loginCheck(User user) {
        if (!userRepository.findByEmail(user.getEmail()).isEmpty()) {
            User actUser = userRepository.findByEmail(user.getEmail()).get();
            return getPasswordEncoder().matches(
                    getPasswordEncoder().encode(user.getPassword()),
                    actUser.getPassword());
        } else return false;
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
}
