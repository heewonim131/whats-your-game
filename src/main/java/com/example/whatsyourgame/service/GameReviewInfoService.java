package com.example.whatsyourgame.service;

import com.example.whatsyourgame.entity.GameReviewInfo;
import com.example.whatsyourgame.repository.GameReviewInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class GameReviewInfoService {

    @Autowired
    private GameReviewInfoRepository gameReviewInfoRepository;

    public Optional<GameReviewInfo> findGameReviewInfoByGameId(Long gameId){
        return gameReviewInfoRepository.findGameReviewInfoByGameId(gameId);
    }

    public Long update(GameReviewInfo gameReviewInfo, int reviewCnt, int reviewScore) {
        gameReviewInfo.update(gameReviewInfo.getReviewCnt() + reviewCnt, reviewScore);
        return gameReviewInfo.getId();
    }

}
