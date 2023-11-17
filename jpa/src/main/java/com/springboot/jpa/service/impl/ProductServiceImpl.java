package com.springboot.jpa.service.impl;

import com.springboot.jpa.data.dao.ProductDao;
import com.springboot.jpa.data.dto.ProductDto;
import com.springboot.jpa.data.dto.ProductResponseDto;
import com.springboot.jpa.data.entity.Product;
import com.springboot.jpa.service.ProductService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;

    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public ProductResponseDto getProduct(Long number) {
        Product product = productDao.selectProduct(number);

        ProductResponseDto productResponseDto = new ProductResponseDto();

        productResponseDto.setNumber(product.getNumber());
        productResponseDto.setName(product.getName());
        productResponseDto.setPrice(productResponseDto.getPrice());
        productResponseDto.setStock(productResponseDto.getStock());

        return productResponseDto;
    }

    @Override
    public ProductResponseDto saveProduct(ProductDto productDto) {

        Product product = new Product();

        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());

        Product savedProduct = productDao.insertProduct(product);

        ProductResponseDto productResponseDto = new ProductResponseDto();

        productResponseDto.setNumber(savedProduct.getNumber());
        productResponseDto.setName(savedProduct.getName());
        productResponseDto.setPrice(savedProduct.getPrice());
        productResponseDto.setStock(savedProduct.getStock());

        return productResponseDto;
    }

    @Override
    public ProductResponseDto changeProductName(Long number, String name) throws Exception {
        Product chagedProduct = productDao.updateProductName(number, name);

        ProductResponseDto productResponseDto = new ProductResponseDto();

        productResponseDto.setNumber(chagedProduct.getNumber());
        productResponseDto.setName(chagedProduct.getName());
        productResponseDto.setPrice(productResponseDto.getPrice());
        productResponseDto.setStock(productResponseDto.getStock());

        return productResponseDto;
    }

    @Override
    public void deleteProduct(Long number) throws Exception {
        productDao.deleteProduct(number);
    }
}
