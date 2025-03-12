package com.megarepuestos.megarepuestos.service.impl;

import com.megarepuestos.megarepuestos.exception.custom.BadRequestException;
import com.megarepuestos.megarepuestos.exception.error.Error;
import com.megarepuestos.megarepuestos.model.ImageType;
import com.megarepuestos.megarepuestos.model.Product;
import com.megarepuestos.megarepuestos.model.User;
import com.megarepuestos.megarepuestos.repository.ProductRepository;
import com.megarepuestos.megarepuestos.repository.UserRepository;
import com.megarepuestos.megarepuestos.repository.specification.ProductSpec;
import com.megarepuestos.megarepuestos.service.ImageService;
import com.megarepuestos.megarepuestos.service.ProductService;
import com.megarepuestos.megarepuestos.service.dto.request.ProductDTOin;
import com.megarepuestos.megarepuestos.service.dto.request.ProductFilterDTO;
import com.megarepuestos.megarepuestos.service.dto.response.ProductDTO;
import com.megarepuestos.megarepuestos.service.mapper.ProductMapper;
import com.megarepuestos.megarepuestos.util.AuthSupport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    private final ImageService imageService;

    public ProductServiceImpl(ProductRepository productRepository, UserRepository userRepository, ImageService imageService) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.imageService = imageService;
    }

    @Override
    public ProductDTO create(ProductDTOin dto) {
        Long userId = AuthSupport.getUserId();
        Optional<User> user = userRepository.findById(userId);
        Product product =  ProductMapper.MAPPER.toEntity(dto);
        product = productRepository.save(product);
        Long imageId = imageService.uploadImage(dto.getImage(), ImageType.PRODUCT, product.getId());
        product.setImageId(imageId);
        product = productRepository.save(product);
        ProductDTO productDTO = ProductMapper.MAPPER.toDto(product);
        productDTO.setS3Url(imageService.getS3url(product.getId(), ImageType.PRODUCT));
        return productDTO;
    }

    @Override
    public ProductDTO getById(Long id) {
        Product product = getProduct(id);
        ProductDTO dto = ProductMapper.MAPPER.toDto(product);
        String s3Url = imageService.getS3url(id, ImageType.PRODUCT);
        dto.setS3Url(s3Url);
        return dto;
    }

    @Override
    public Page<ProductDTO> getAll(ProductFilterDTO filter, Pageable pageable) {
        Specification<Product> spec = ProductSpec.getSpec(filter);
        Page<Product> page = productRepository.findAll(spec, pageable);
        Page<ProductDTO> dtoPage = page.map(product -> {
            try {
                ProductDTO dto = ProductMapper.MAPPER.toDto(product);
                String s3Url = imageService.getS3url(product.getId(), ImageType.PRODUCT);
                dto.setS3Url(s3Url);
                return dto;
            } catch (Exception e) {
                System.err.println("Error al obtener la URL de la imagen para ID " + product.getId() + ": " + e.getMessage());
                ProductDTO dto = ProductMapper.MAPPER.toDto(product);
                dto.setS3Url(null);
                return dto;
            }
        });

        return dtoPage;
    }


    @Override
    public ProductDTO update(Long id, ProductDTOin dto) {
        Product product = getProduct(id);
        Product productUpdated = ProductMapper.MAPPER.toEntity(dto);
        ProductMapper.MAPPER.update(product, productUpdated);
        if (dto.getImage() != null) {
            imageService.deleteImage(product.getId(), ImageType.PRODUCT);
            Long imageId = imageService.uploadImage(dto.getImage(), ImageType.PRODUCT, product.getId());
            product.setImageId(imageId);
            productRepository.save(product);
        }
        productRepository.save(product);
        ProductDTO productDTO = ProductMapper.MAPPER.toDto(product);
        productDTO.setS3Url(imageService.getS3url(product.getId(), ImageType.PRODUCT));
        return productDTO;
    }

    @Override
    public void delete(Long id)  {
        Long userId = AuthSupport.getUserId();
        Product product = getProduct(id);
        if(userId == null){
            throw new BadRequestException(Error.USER_NOT_LOGIN);
        }
        productRepository.delete(product);
    }

    private Product getProduct(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            throw new BadRequestException(Error.PRODUCT_NOT_FOUND);
        }
        return productOptional.get();
    }

}
