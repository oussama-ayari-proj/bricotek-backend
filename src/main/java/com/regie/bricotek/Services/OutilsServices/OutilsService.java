package com.regie.bricotek.Services.OutilsServices;

import com.regie.bricotek.entities.Outils.Outil;
import com.regie.bricotek.entities.Outils.OutilRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OutilsService {
    @Autowired
    OutilRepository outilRepository;

    public Outil addOutil(Outil outil){
        return outilRepository.save(outil);
    }
    public List<Outil> getAllOutils(){
        return outilRepository.findAll();
    }

    public void deleteOutil(String outilId){ outilRepository.deleteById(outilId);}
    public void modify(String outilId,Outil outil){
        Outil outil1=outilRepository.findByOutilId(outilId).get();
        outil1.setImageData(outil.getImageData());
        outil1.setCategorieOutils(outil.getCategorieOutils());
        outil1.setEtat(outil.isEtat());
        outil1.setNom(outil.getNom());
        outil1.setImgPath(outil.getImgPath());
        outil1.setVideoPath(outil.getVideoPath());
        outilRepository.save(outil1);
    }
    public Outil getOutilById(String outilsId){
        return outilRepository.findByOutilId(outilsId).get();
    }
}
