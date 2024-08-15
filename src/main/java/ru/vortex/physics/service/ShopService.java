package ru.vortex.physics.service;

import org.springframework.stereotype.Service;
import ru.vortex.physics.model.shop.Product;

import java.util.ArrayList;

@Service
public class ShopService {


    public ArrayList<Product> getProducts(){
        ArrayList<Product> products = new ArrayList<Product>();
        products.add(new Product("Подписька",1, 145, "Блин, купи пж пж", 0));
        products.add(new Product("Эффекты",2, 95, "Вжух!", 0));
        products.add(new Product("Изменение размера",3, 445, "Был маленький(среднестатистический), стал большой", 0));
        products.add(new Product("Иконки", 4,145, "Мона Лиза", 0));
        products.add(new Product("Жертва", 5,100, "Аборта (?)", 50));
        products.add(new Product("Проходка", 6,95, "по Доске", 0));
        products.add(new Product("Инспектор", 7,45, "Гаджет, кто же еще?", 0));
        products.add(new Product("Перенос аккаунта", 8,145, "Для любителей шизофрении", 0));
        products.add(new Product("Кастомные пластинки", 9,95, "SHOW MUST GO ON", 0));
        products.add(new Product("Разбан", 10,295, "I always come back", 0));
        products.add(new Product("Разбан чата", 11,145, "Это тантум верде форте", 0));
        return products;
    }

}
