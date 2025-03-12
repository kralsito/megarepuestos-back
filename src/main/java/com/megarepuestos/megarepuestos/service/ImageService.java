package com.megarepuestos.megarepuestos.service;


import com.megarepuestos.megarepuestos.model.ImageType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    Long uploadImage(MultipartFile file, ImageType type, Long modelId);

    String getS3url(Long modelId, ImageType image);

    void deleteImage(Long modelId, ImageType type);

}
