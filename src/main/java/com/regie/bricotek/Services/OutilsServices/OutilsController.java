package com.regie.bricotek.Services.OutilsServices;

import com.regie.bricotek.entities.Outils.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("outils")
@RequiredArgsConstructor
@Tag(name = "Outils")
public class OutilsController {
    @Autowired
    private OutilsService outilsService;
    @Autowired
    OutilRepository outilRepository;
    @Autowired
    private ImageDataService imageDataService;

    @Autowired
    ImageDataRepository imageDataRepository;

    @PostMapping(value = "addimage",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> uploadImage(
            @RequestParam("image") MultipartFile file
            ,@RequestParam("outil_id") Long outilId
            ) throws IOException {
        Outil outil = outilRepository.findByOutilId(outilId).get();
        System.out.println(outil.getImageData());
        if(outil.getImageData()!=null){
            System.out.println("image!!!!!!!");
        }
        imageDataService.delete(outil.getImageData());
        ImageData imageData = ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(file.getBytes())
                .build();
        imageDataService.uploadImage(imageData);
        outil.setImageData(imageData);
        outilsService.addOutil(outil);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @PostMapping(value = "addOutil")
    public ResponseEntity<?> addOutil(Outil outil){
        Outil outil1=outilsService.addOutil(outil);
        return ResponseEntity.status(HttpStatus.OK).body(
                outil1.getOutilId()
        );
    }
    @GetMapping("allOutils") ResponseEntity<?> getAllOutils(){
        List<Outil> outils=outilsService.getAllOutils();
        List<OutilResponse> outilResponses=new ArrayList<>();
        for(Outil outil : outils){
            if(outil.getImageData()!=null){
                outilResponses.add(
                        OutilResponse.builder()
                                .etat(outil.isEtat())
                                .image(outil.getImageData().getId())
                                .nom(outil.getNom())
                                .videoPath(outil.getVideoPath())
                                .outilId(outil.getOutilId())
                                .categorieOutils(outil.getCategorieOutils())
                                .build()
                );
            }else{
                outilResponses.add(
                        OutilResponse.builder()
                                .etat(outil.isEtat())
                                .nom(outil.getNom())
                                .videoPath(outil.getVideoPath())
                                .outilId(outil.getOutilId())
                                .categorieOutils(outil.getCategorieOutils())
                                .build()
                );
            }

        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(outilResponses);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<?>  getImageByName(@PathVariable("id") Long id){
        byte[] image = imageDataService.getImage(id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(image);
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteOutil(Long outilId){
        try{
            outilsService.deleteOutil(outilId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(0);
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(1);
    }

    @PutMapping(value = "modifyOutil")
    public ResponseEntity<?> modifyOutil(Long outilsId,Outil outil){
        outilsService.modify(outilsId,outil);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping(value = "outilById")
    public ResponseEntity<?> getById(Long outilsId){
        Outil outil=outilsService.getOutilById(outilsId);
        if(outil.getImageData()!=null){
            return ResponseEntity.status(HttpStatus.OK).body(
                    OutilResponse.builder()
                            .etat(outil.isEtat())
                            .nom(outil.getNom())
                            .videoPath(outil.getVideoPath())
                            .image(outil.getImageData().getId())
                            .outilId(outil.getOutilId())
                            .categorieOutils(outil.getCategorieOutils())
                            .build()
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                OutilResponse.builder()
                    .etat(outil.isEtat())
                    .nom(outil.getNom())
                    .videoPath(outil.getVideoPath())
                    .outilId(outil.getOutilId())
                    .categorieOutils(outil.getCategorieOutils())
                    .build()
        );
    }

}
