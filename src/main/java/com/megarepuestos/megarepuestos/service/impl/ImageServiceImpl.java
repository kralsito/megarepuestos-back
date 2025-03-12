package com.megarepuestos.megarepuestos.service.impl;


import com.megarepuestos.megarepuestos.model.Image;
import com.megarepuestos.megarepuestos.model.ImageType;
import com.megarepuestos.megarepuestos.repository.ImageRepository;
import com.megarepuestos.megarepuestos.service.ImageService;
import com.megarepuestos.megarepuestos.util.ImageUtil;
import com.megarepuestos.megarepuestos.util.S3Support;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    @Value("${aws.s3.bucket.name}")
    private String bucketName;

    private final ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public Long uploadImage(MultipartFile file, ImageType type, Long modelId) {
        Image image = ImageUtil.createImage(file, type, modelId);
        image = imageRepository.save(image);
        S3Support.upload(ImageUtil.buildPath(image), file);
        return image.getId();
    }

    @Override
    public String getS3url(Long modelId, ImageType type) {
        List<Image> imageList = imageRepository.findByModelIdAndType(modelId, type);
        /**
         * ahora agarra la primera
         * pero esta preparado para devolver muchas,
         * para el caso de una publicacion con muchas fotos
         */
        Image image = imageList.get(0);
        String imageUrl = S3Support.getS3url(image);
        return imageUrl;
    }

    @Override
    public void deleteImage(Long modelId, ImageType type) {
        List<Image> imageList = imageRepository.findByModelIdAndType(modelId, type);
        imageList.forEach(imageRepository::delete);
    }

}
