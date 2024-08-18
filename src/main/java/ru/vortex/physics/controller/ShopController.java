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

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getProducts")
    @ResponseBody
    public List<Product> getProducts() {
        return shopService.getProducts();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getProduct")
    @ResponseBody
    public Product getProduct(@RequestParam  Long id) {
        return shopService.getProductById(id);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/createProduct")
    @ResponseBody
    public void createProduct(@RequestBody Product product) {
        shopService.saveProduct(product);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/deleteProduct")
    @ResponseBody
    public void deleteProduct(@RequestBody Product product) {
        shopService.deleteProduct(product);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PatchMapping("/patchProduct")
    @ResponseBody
    public void patchProduct(@RequestBody Product product) {
        shopService.patchProduct(product);
    }
}