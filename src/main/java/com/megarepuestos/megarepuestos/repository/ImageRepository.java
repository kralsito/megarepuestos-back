package com.megarepuestos.megarepuestos.repository;


import com.megarepuestos.megarepuestos.model.Image;
import com.megarepuestos.megarepuestos.model.ImageType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByModelIdAndType(@Param("modelId") Long modelId, @Param("type") ImageType imageType);
}
