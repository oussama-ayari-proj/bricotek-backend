package com.regie.bricotek.User;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUserId(String userId);

    @Query(value = "SELECT num_adh FROM utilisateur ORDER BY num_adh DESC LIMIT 1", nativeQuery = true)
    String findLastId();
}
