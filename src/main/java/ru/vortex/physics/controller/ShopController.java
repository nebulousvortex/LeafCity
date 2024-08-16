package ru.vortex.physics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.vortex.physics.model.shop.Product;
import ru.vortex.physics.service.PaymentService;
import ru.vortex.physics.service.ShopService;

import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    private ShopService shopService;

    @GetMapping("/getProducts")
    @ResponseBody
    public List<Product> getProducts() {
        return shopService.getProducts();
    }

    @GetMapping("/getProduct")
    @ResponseBody
    public Product getProduct(@RequestParam  Long id) {
        return shopService.getProductById(id);
    }

    @PostMapping("/createProducts")
    @ResponseBody
    public void createProducts() {
        shopService.saveProducts();
    }

    @PostMapping("/createProduct")
    @ResponseBody
    public void createProduct(@RequestBody Product product) {
        shopService.saveProduct(product);
    }
}

