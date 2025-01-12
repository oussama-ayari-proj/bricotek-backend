package com.regie.bricotek.entities.Outils;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OutilRepository extends JpaRepository<Outil,Long> {
    Optional<Outil> findByOutilId(Long id);
}
