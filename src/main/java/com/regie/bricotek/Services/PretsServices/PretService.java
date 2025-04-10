package com.regie.bricotek.Services.PretsServices;

import com.regie.bricotek.User.User;
import com.regie.bricotek.User.UserRepository;
import com.regie.bricotek.entities.Outils.Outil;
import com.regie.bricotek.entities.Outils.OutilRepository;
import com.regie.bricotek.entities.Pret.Etat;
import com.regie.bricotek.entities.Pret.Pret;
import com.regie.bricotek.entities.Pret.PretRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    public void add(String outilId, String userId, LocalDate dateRetour) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Outil outil = outilRepository.findById(outilId)
                .orElseThrow(() -> new RuntimeException("Outils not found"));
        outil.setEtat(false);
        Pret prets = new Pret();
        prets.setUser(user);
        prets.setOutil(outil);
        prets.setDateRetour(dateRetour);
        prets.setDateDemande(LocalDateTime.now());
        prets.setEtat(Etat.ATTENTE);
        pretRepository.save(prets);
    }

    public void valider(Integer pretId){
        Pret pret=pretRepository.findById(pretId).get();
        pret.setEtat(Etat.CONFIRME);
        pretRepository.save(pret);
    }

    public void refuser(Integer pretId){
        Pret pret=pretRepository.findById(pretId).get();
        pret.setEtat(Etat.REFUSE);
        pretRepository.save(pret);
    }
    public void delete(Integer pretId){
        pretRepository.deleteById(pretId);
    }

    public void attente(Integer pretId) {
        Pret pret=pretRepository.findById(pretId).get();
        pret.setEtat(Etat.ATTENTE);
        pretRepository.save(pret);
    }
}
