package com.example.whatsyourgame.repository;

import com.example.whatsyourgame.entity.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findAll();
    Page<Game> findAll(Pageable pageable);
    Page<Game> findGamesByNameContaining(String search, Pageable pageable);
    List<Game> findTop4GamesByOrderByReleasedAtDesc();
    List<Game> findTop6GamesByOrderByIdAsc();
    @Query(value = "SELECT g.* FROM wish w join game g where w.game_id = g.id and user_id = :id", nativeQuery = true)
    List<Game> findWishList(Long id);   // 찜한 게임
}
