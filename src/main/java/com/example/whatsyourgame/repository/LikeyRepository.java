package com.example.whatsyourgame.repository;

import com.example.whatsyourgame.entity.Likey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeyRepository extends JpaRepository<Likey, Long> {

    Optional<Likey> findLikeyByUserIdAndReviewId(Long userId, Long reviewId);

    @Modifying(clearAutomatically = true)
    @Query(value = "delete from likey l where l.id = :likeyId", nativeQuery = true)
    int delete(Long likeyId);

}
