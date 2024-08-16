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

    public void saveProducts(){
        ArrayList<Product> products = new ArrayList<Product>();
        products.add(new Product("Подписька",1L, 145, "Блин, купи пж пж", 0));
        products.add(new Product("Эффекты",2L, 95, "Вжух!", 0));
        products.add(new Product("Изменение размера",3L, 445, "Был маленький(среднестатистический), стал большой Был маленький(среднестатистический), стал большой Был маленький(среднестатистический), стал большой Был маленький(среднестатистический), стал большой Был маленький(среднестатистический), стал большой Был маленький(среднестатистический), стал большой Был маленький(среднестатистический), стал большой Был ма", 0));
        products.add(new Product("Иконки", 4L,145, "Мона Лиза", 0));
        products.add(new Product("Жертва", 5L,100, "Аборта (?)", 50));
        products.add(new Product("Проходка", 6L,95, "по Доске", 0));
        products.add(new Product("Инспектор", 7L,45, "Гаджет, кто же еще?", 0));
        products.add(new Product("Перенос аккаунта", 8L,145, "Для любителей шизофрении", 0));
        products.add(new Product("Кастомные пластинки", 9L,95, "SHOW MUST GO ON", 0));
        products.add(new Product("Разбан", 10L,295, "I always come back", 0));
        products.add(new Product("Разбан чата", 11L,145, "Это тантум верде форте", 0));
        for(Product product: products){
            productRepository.save(product);
        }
    }

}
