package com.example.whatsyourgame.service;

import com.example.whatsyourgame.entity.GameReviewInfo;
import com.example.whatsyourgame.repository.GameReviewInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GameReviewInfoService {

    @Autowired
    private GameReviewInfoRepository gameReviewInfoRepository;

    public Long update(Long gameId, int reviewCnt, int reviewScore) {
        GameReviewInfo gameReviewInfo = gameReviewInfoRepository.findGameReviewInfoByGameId(gameId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게임 정보가 존재하지 않습니다. id=" + gameId));
        gameReviewInfo.update(gameReviewInfo.getReviewCnt() + reviewCnt, reviewScore);
        return gameReviewInfo.getId();
    }

}
