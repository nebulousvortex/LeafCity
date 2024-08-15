package ru.vortex.physics.service;

import org.springframework.stereotype.Service;
import ru.vortex.physics.model.shop.Product;

import java.util.ArrayList;

@Service
public class ShopService {


    public ArrayList<Product> getProducts(){
        ArrayList<Product> products = new ArrayList<Product>();
        products.add(new Product("Подписька", 145, "Блин, купи пж пж", 0));
        products.add(new Product("Эффекты", 95, "Вжух!", 0));
        products.add(new Product("Изменение размера", 445, "Был маленький(среднестатистический), стал большой", 0));
        products.add(new Product("Иконки", 145, "Мона Лиза", 0));
        products.add(new Product("Жертва", 100, "Аборта (?)", 50));
        products.add(new Product("Проходка", 95, "по Доске", 0));
        products.add(new Product("Инспектор", 45, "Гаджет, кто же еще?", 0));
        products.add(new Product("Перенос аккаунта", 145, "Для любителей шизофрении", 0));
        products.add(new Product("Кастомные пластинки", 95, "SHOW MUST GO ON", 0));
        products.add(new Product("Разбан", 295, "I always come back", 0));
        products.add(new Product("Разбан чата", 145, "Это тантум верде форте", 0));
        return products;
    }

}
