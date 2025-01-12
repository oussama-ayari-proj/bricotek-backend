package com.regie.bricotek.Services.OutilsServices;

import com.regie.bricotek.entities.Outils.ImageData;
import com.regie.bricotek.entities.Outils.ImageDataRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageDataService {

    @Autowired
    private ImageDataRepository imageDataRepository;

    public void uploadImage(ImageData imageData) throws IOException {

        imageDataRepository.save(imageData);
    }

    @Transactional
    public byte[] getImage(Long id) {
        Optional<ImageData> dbImage = imageDataRepository.findById(id);
        return dbImage.get().getImageData();
    }

    public void delete(ImageData imageData) {
        if(imageData!=null)
            imageDataRepository.delete(imageData);
    }
}
