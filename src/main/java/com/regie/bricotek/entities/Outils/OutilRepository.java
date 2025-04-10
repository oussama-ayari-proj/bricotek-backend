package com.regie.bricotek.entities.Outils;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OutilRepository extends JpaRepository<Outil,String> {
    Optional<Outil> findByOutilId(String id);

    @Query(value = "SELECT outil_id FROM outils ORDER BY outil_id DESC LIMIT 1", nativeQuery = true)
    String findLastId();
}
