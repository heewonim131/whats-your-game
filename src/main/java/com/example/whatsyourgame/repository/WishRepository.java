package com.example.whatsyourgame.repository;

import com.example.whatsyourgame.entity.Wish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishRepository extends JpaRepository<Wish, Long> {
    Optional<Wish> findWishByUserIdAndGameId(Long userId, Long GameId);
}
