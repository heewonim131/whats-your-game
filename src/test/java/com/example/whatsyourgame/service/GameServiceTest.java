package com.example.whatsyourgame.service;

import com.example.whatsyourgame.entity.Game;
import com.example.whatsyourgame.entity.Publisher;
import com.example.whatsyourgame.repository.GameRepository;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
public class GameServiceTest {

    @InjectMocks
    private GameService gameService;

    @Mock
    private GameRepository gameRepository;

    @Test
    void save() {
        Game game = Game.builder()
                .name("롤")
                .description("롤 설명")
                .img("img")
                .genre("AOS")
                .platform("온라인")
                .site("#")
                .releasedAt(LocalDate.now())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .publisher(new Publisher())
                .build();

        when(this.gameRepository.save(any(Game.class)))
                .then(AdditionalAnswers.returnsFirstArg());

        Game result = this.gameService.save(game);

        assertEquals("롤", result.getName());
    }
}
