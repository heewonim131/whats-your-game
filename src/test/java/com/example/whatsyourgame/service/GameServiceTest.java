package com.example.whatsyourgame.service;

import com.example.whatsyourgame.entity.Game;
import com.example.whatsyourgame.repository.GameRepository;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class GameServiceTest {

    @InjectMocks
    private GameService gameService;

    @Mock
    private GameRepository gameRepository;

    @BeforeEach
    void beforeEach() {
        Game game1 = new Game();
        game1.setName("롤");
        gameRepository.save(game1);

        Game game2 = new Game();
        game2.setName("배그");
        gameRepository.save(game1);
    }

    @Test
    void findAll() {
        List<Game> list = gameService.findAll();
        assertEquals(2, list.size());
    }

    @Test
    void findById() {
        Optional<Game> result = gameService.findById(1L);
        assertEquals("롤", result.get().getName());
    }
}
