package com.megarepuestos.megarepuestos.service;

import com.megarepuestos.megarepuestos.service.dto.request.ProductDTOin;
import com.megarepuestos.megarepuestos.service.dto.request.ProductFilterDTO;
import com.megarepuestos.megarepuestos.service.dto.response.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


public interface ProductService {

    ProductDTO create(ProductDTOin dto);

    ProductDTO getById(Long id);

    Page<ProductDTO> getAll(ProductFilterDTO filter, Pageable pageable);

    ProductDTO update(Long id, ProductDTOin dto);

    void delete(Long id);
}
