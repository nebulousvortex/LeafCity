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
        var product =  productRepository.findById(id);
        return product.orElse(null);
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
        if(product.getSale() >= 0f){
            existingProduct.setSale(product.getSale());
        }
        if(product.getCommand()!= null){
            existingProduct.setCommand(product.getCommand());
        }
        if(product.getNotify()!= null){
            existingProduct.setNotify(product.getNotify());
        }
        if(product.getDuration()!= null){
            existingProduct.setDuration(product.getDuration());
        }
        if(product.getCategory()!= null){
            existingProduct.setCategory(product.getCategory());
        }
        if(product.getImageUrl() != null){
            existingProduct.setImageUrl(product.getImageUrl());
        }
        productRepository.save(existingProduct);
    }

}
