package com.example.whatsyourgame.repository;

import com.example.whatsyourgame.entity.GameReviewInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameReviewInfoRepository extends JpaRepository<GameReviewInfo, Long> {
    Optional<GameReviewInfo> findGameReviewInfoByGameId(Long gameId);
}
