package com.example.whatsyourgame.service;

import com.example.whatsyourgame.entity.Wish;
import com.example.whatsyourgame.repository.WishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class WishService {

    @Autowired
    private WishRepository wishRepository;

    public Optional<Wish> findWishByUserIdAndGameId(Long userId, Long gameId) {
        return wishRepository.findWishByUserIdAndGameId(userId, gameId);
    }

    public Wish wish(Wish wish) {
        wish.setCreatedAt(LocalDateTime.now());
        wish.setUpdatedAt(LocalDateTime.now());
        return wishRepository.save(wish);
    }

    public void deleteWish(Wish wish) {
        wishRepository.delete(wish);
    }
}
