package com.example.whatsyourgame.repository;

import com.example.whatsyourgame.entity.Game;
import com.example.whatsyourgame.entity.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

//    List<Game> findTop4OrderByReleasedAtDesc();

    @Query(value = "select * from game order by released_at desc limit 4;", nativeQuery = true)
    List<Game> findLatestTop4();

    @Query(value = "select * from game order by id asc limit 6;", nativeQuery = true)
    List<Game> findPopularTop6();
}
