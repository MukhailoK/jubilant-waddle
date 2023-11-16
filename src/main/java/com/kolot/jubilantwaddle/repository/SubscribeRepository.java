package com.kolot.jubilantwaddle.repository;

import com.kolot.jubilantwaddle.model.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {
    Optional<Subscribe> findByName(String name);
}
