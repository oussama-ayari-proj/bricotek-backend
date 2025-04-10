package com.regie.bricotek.User;

import com.regie.bricotek.entities.Outils.OutilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IdGenerator {

    private static UserRepository repository;
    private static OutilRepository outilRepository;

    @Autowired
    public IdGenerator(UserRepository repo,OutilRepository outil_repo) {
        repository = repo;
        outilRepository=outil_repo;
    }


    public static String generateNextId() {
        String lastId = repository.findLastId(); // e.g. "A00023"
        int nextNumber = 1;

        if (lastId != null && lastId.startsWith("A")) {
            nextNumber = Integer.parseInt(lastId.substring(1)) + 1;
        }

        return String.format("A%05d", nextNumber);
    }

    public static String generateNextOutilId() {
        String lastId = outilRepository.findLastId(); // e.g. "A00023"
        int nextNumber = 1;

        if (lastId != null && lastId.startsWith("OB")) {
            nextNumber = Integer.parseInt(lastId.substring(1)) + 1;
        }

        return String.format("OB%05d", nextNumber);
    }
}