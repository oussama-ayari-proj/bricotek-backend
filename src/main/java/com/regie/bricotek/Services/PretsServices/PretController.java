package com.regie.bricotek.Services.PretsServices;

import com.regie.bricotek.entities.Pret.Pret;
import com.regie.bricotek.entities.Pret.PretResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("prets")
@RequiredArgsConstructor
@Tag(name = "Prets")
public class PretController {
    @Autowired
    PretService pretService;
    @GetMapping("getAllPrets")
    public ResponseEntity<?> getAll(){
        List<PretResponse> pretResponses=new ArrayList<>();
        List<Pret> prets= pretService.getAll();
        for(Pret p : prets){
            pretResponses.add(PretResponse
                    .builder()
                            .pretId(p.getPretId())
                            .outil(p.getOutil().getOutilId()+" "+p.getOutil().getNom())
                            .user(p.getUser().getUserId()+" "+p.getUser().getNom()+" "+p.getUser().getPrenom())
                            .etat(p.getEtat().toString())
                            .dateRetour(p.getDateRetour().toString())
                            .dateDemande(p.getDateDemande().toString())
                    .build());
        }
        return ResponseEntity.ok(pretResponses);
    }

    @PostMapping("addPret")
    public ResponseEntity<?> add(@RequestParam String outilId,@RequestParam String userId,@RequestParam LocalDate dateRetour){
        pretService.add(outilId,userId,dateRetour);
        return ResponseEntity.ok("Ajout avec succes");
    }
    @PutMapping("valider")
    public ResponseEntity<?> valider(Integer id){
        pretService.valider(id);
        return ResponseEntity.ok("Validation avec succes");
    }

    @PutMapping("refuser")
    public ResponseEntity<?> refuser(Integer id){
        pretService.refuser(id);
        return ResponseEntity.ok("Refus avec succes");
    }

    @PutMapping("attente")
    public ResponseEntity<?> attente(Integer id){
        pretService.attente(id);
        return ResponseEntity.ok("En Attente avec succes");
    }
    @DeleteMapping("delete")
    public ResponseEntity<?> delete(Integer id){        pretService.delete(id);
        return ResponseEntity.ok("Suppression avec succes");
    }
}
