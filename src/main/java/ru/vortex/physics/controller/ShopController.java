package ru.vortex.physics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vortex.physics.model.shop.Product;
import ru.vortex.physics.service.PaymentService;
import ru.vortex.physics.service.ShopService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    private ShopService shopService;

    @CrossOrigin(origins = {"http://localhost:3000" , "https://leafcity.vercel.app", "http://192.168.100.7:3000"})
    @GetMapping("/getProducts")
    @ResponseBody
    public List<Product> getProducts() {
        return shopService.getProducts();
    }

    @CrossOrigin(origins = {"http://localhost:3000" , "https://leafcity.vercel.app", "http://192.168.100.7:3000"})
    @GetMapping("/getProduct")
    @ResponseBody
    public Product getProduct(@RequestParam  Long id) {
        return shopService.getProductById(id);
    }

    @CrossOrigin(origins = {"http://localhost:3000" , "https://leafcity.vercel.app", "http://192.168.100.7:3000"})
    @PostMapping("/createProduct")
    @ResponseBody
    public ResponseEntity<String> createProduct(@RequestBody Product product) {
        shopService.saveProduct(product);
        return ResponseEntity.ok("Ok");
    }

    @CrossOrigin(origins = {"http://localhost:3000" , "https://leafcity.vercel.app", "http://192.168.100.7:3000"})
    @DeleteMapping("/deleteProduct")
    @ResponseBody
    public ResponseEntity<String> deleteProduct(@RequestBody Product product) {
        shopService.deleteProduct(product);
        return ResponseEntity.ok("Ok");
    }

    @CrossOrigin(origins = {"http://localhost:3000" , "https://leafcity.vercel.app", "http://192.168.100.7:3000"})
    @PatchMapping("/patchProduct")
    @ResponseBody
    public ResponseEntity<String> patchProduct(@RequestBody Product product) {
        shopService.patchProduct(product);
        return ResponseEntity.ok("Ok");
    }
}