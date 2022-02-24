package com.example.whatsyourgame.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.example.whatsyourgame.entity.User;
import com.example.whatsyourgame.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Test
    void 회원가입() {
        User user = User.builder()
                .name("user")
                .email("user@test.com")
                .password("12345678")
                .build();

        when(this.userRepository.save(any(User.class)))
                .then(AdditionalAnswers.returnsFirstArg());

        User result = this.userService.save(user);

        assertEquals("user",  result.getName());
        assertEquals("user@test.com",  result.getEmail());
        assertTrue(getPasswordEncoder()
                .matches("12345678", result.getPassword()));
    }

}
