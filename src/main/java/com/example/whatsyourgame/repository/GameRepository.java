package com.example.whatsyourgame.repository;

import com.example.whatsyourgame.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    @Query(value = "select * from game order by released_at desc limit 4;", nativeQuery = true)
    List<Game> findLatestTop4();

    @Query(value = "select * from game order by id asc limit 6;", nativeQuery = true)
    List<Game> findPopularTop6();
}
