package ru.vortex.physics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.vortex.physics.model.shop.Product;
import ru.vortex.physics.service.PaymentService;
import ru.vortex.physics.service.ShopService;

import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping("/api/v1/shop")
public class ShopController {
    @Autowired
    private ShopService shopService;

    @GetMapping("/getProducts")
    @CrossOrigin(origins = "http://localhost:3000")
    @ResponseBody
    public List<Product> getProducts() {
        return shopService.getProducts();
    }

    @GetMapping("/getProduct")
    @CrossOrigin(origins = "http://localhost:3000")
    @ResponseBody
    public Product getProduct(@RequestParam  Long id) {
        return shopService.getProductById(id);
    }

    @PostMapping("/createProducts")
    @CrossOrigin(origins = "http://localhost:3000")
    @ResponseBody
    public void createProducts() {
        shopService.saveProducts();
    }

    @PostMapping("/createProduct")
    @CrossOrigin(origins = "http://localhost:3000")
    @ResponseBody
    public void createProduct(@RequestBody Product product) {
        shopService.saveProduct(product);
    }
}

