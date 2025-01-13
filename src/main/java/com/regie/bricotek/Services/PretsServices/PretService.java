package com.regie.bricotek.Services.PretsServices;

import com.regie.bricotek.User.User;
import com.regie.bricotek.User.UserRepository;
import com.regie.bricotek.entities.Outils.Outil;
import com.regie.bricotek.entities.Outils.OutilRepository;
import com.regie.bricotek.entities.Pret.Pret;
import com.regie.bricotek.entities.Pret.PretRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PretService {
    @Autowired
    PretRepository pretRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    OutilRepository outilRepository;

    public List<Pret> getAll(){
        return pretRepository.findAll();
    }

    public void add(Long outilId, Integer userId, Date dateRetour) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Outil outils = outilRepository.findById(outilId)
                .orElseThrow(() -> new RuntimeException("Outils not found"));
        outils.setEtat(false);
        Pret prets = new Pret();
        prets.setUser(user);
        prets.setOutil(outils);
        prets.setDateRetour(dateRetour);

        pretRepository.save(prets);
    }
}
