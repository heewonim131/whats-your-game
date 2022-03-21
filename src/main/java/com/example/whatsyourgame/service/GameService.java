package com.example.whatsyourgame.service;

import com.example.whatsyourgame.entity.*;
import com.example.whatsyourgame.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public List<Game> findAll() {
        return gameRepository.findAll();
    }

    public Optional<Game> findById(Long id) {
        return gameRepository.findById(id);
    }

    public List<Game> findTop4GamesByOrderByReleasedAtDesc() {
        return gameRepository.findTop4GamesByOrderByReleasedAtDesc();
    }

    public List<Game> findTop6GamesByOrderByIdAsc() {
        return gameRepository.findTop6GamesByOrderByIdAsc();
    }

    public List<Game> findWishList(Long id) {
        return gameRepository.findWishList(id);
    }

}
