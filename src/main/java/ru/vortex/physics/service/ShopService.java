package ru.vortex.physics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vortex.physics.model.shop.Product;
import ru.vortex.physics.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShopService {
    @Autowired
    ProductRepository productRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id){
        return productRepository.findById(id).orElseThrow();
    }

    public void saveProduct(Product product){ productRepository.save(product);}
    public void deleteProduct(Product product){
        productRepository.deleteById(product.getId());
    }

    public void patchProduct(Product product){
        Product existingProduct = productRepository.findById(product.getId()).orElseThrow();
        if(product.getName() != null){
            existingProduct.setName(product.getName());
        }
        if(product.getDescription() != null){
            existingProduct.setDescription(product.getDescription());
        }
        if(product.getPrice() != 0f){
            existingProduct.setPrice(product.getPrice());
        }
        if(product.getImageUrl() != null){
            existingProduct.setImageUrl(product.getImageUrl());
        }
        productRepository.save(existingProduct);
    }

}
