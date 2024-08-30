package ru.vortex.leafcity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vortex.leafcity.model.shop.Product;
import ru.vortex.leafcity.repository.ProductRepository;

import java.util.List;

@Service
public class ShopService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    DurationService durationService;
    @Autowired
    CategoryService categoryService;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id){
        return productRepository.findById(id).orElse(null);
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
        if(product.getFeatures() != null){
            existingProduct.setFeatures(product.getFeatures());
        }
        if(product.getAbilities() != null){
            existingProduct.setAbilities(product.getAbilities());
        }
        if(product.getAbout() != null){
            existingProduct.setAbout(product.getAbout());
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
            existingProduct.setDuration(durationService.findDuration(product.getDuration().getId()));
        }
        if(product.getCategory()!= null){
            existingProduct.setCategory(categoryService.findCategory(product.getCategory().getId()));
        }
        if(product.getImageUrl() != null){
            existingProduct.setImageUrl(product.getImageUrl());
        }
        productRepository.save(existingProduct);
    }

}
