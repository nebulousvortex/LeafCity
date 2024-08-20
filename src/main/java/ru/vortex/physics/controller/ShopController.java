package ru.vortex.physics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vortex.physics.model.shop.Category;
import ru.vortex.physics.model.shop.Duration;
import ru.vortex.physics.model.shop.Product;
import ru.vortex.physics.service.CategoryService;
import ru.vortex.physics.service.DurationService;
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
    @Autowired
    private DurationService durationService;
    @Autowired
    private CategoryService categoryService;

    @CrossOrigin(origins = {"http://localhost:3000" , "https://leafcity.vercel.app", "http://192.168.100.7:3000"})
    @GetMapping("/products")
    @ResponseBody
    public List<Product> getProducts() {
        return shopService.getProducts();
    }

    @CrossOrigin(origins = {"http://localhost:3000" , "https://leafcity.vercel.app", "http://192.168.100.7:3000"})
    @GetMapping("/product")
    @ResponseBody
    public Product getProduct(@RequestParam  Long id) {
        return shopService.getProductById(id);
    }

    @CrossOrigin(origins = {"http://localhost:3000" , "https://leafcity.vercel.app", "http://192.168.100.7:3000"})
    @PostMapping("/product")
    @ResponseBody
    public ResponseEntity<String> createProduct(@RequestBody Product product) {
        var duration = durationService.findDuration(product.getDuration().getId());
        var category = categoryService.findCategory(product.getCategory().getId());
        product.setCategory(category);
        product.setDuration(duration);
        shopService.saveProduct(product);
        return ResponseEntity.ok("Ok");
    }

    @CrossOrigin(origins = {"http://localhost:3000" , "https://leafcity.vercel.app", "http://192.168.100.7:3000"})
    @DeleteMapping("/product")
    @ResponseBody
    public ResponseEntity<String> deleteProduct(@RequestBody Product product) {
        shopService.deleteProduct(product);
        return ResponseEntity.ok("Ok");
    }

    @CrossOrigin(origins = {"http://localhost:3000" , "https://leafcity.vercel.app", "http://192.168.100.7:3000"})
    @PatchMapping("/product")
    @ResponseBody
    public ResponseEntity<String> patchProduct(@RequestBody Product product) {
        shopService.patchProduct(product);
        return ResponseEntity.ok("Ok");
    }

    @CrossOrigin(origins = {"http://localhost:3000" , "https://leafcity.vercel.app", "http://192.168.100.7:3000"})
    @PostMapping("/category")
    @ResponseBody
    public ResponseEntity<String> createCategory(@RequestBody Category category) {
        categoryService.createCategory(category);
        return ResponseEntity.ok("Ok");
    }

    @CrossOrigin(origins = {"http://localhost:3000" , "https://leafcity.vercel.app", "http://192.168.100.7:3000"})
    @DeleteMapping("/category")
    @ResponseBody
    public ResponseEntity<String> deleteCategory(@RequestBody Category category) {
        categoryService.deleteCategory(category);
        return ResponseEntity.ok("Ok");
    }

    @CrossOrigin(origins = {"http://localhost:3000" , "https://leafcity.vercel.app", "http://192.168.100.7:3000"})
    @PostMapping("/duration")
    @ResponseBody
    public ResponseEntity<String> createDuration(@RequestBody Duration duration) {
        durationService.createDuration(duration);
        return ResponseEntity.ok("Ok");
    }

    @CrossOrigin(origins = {"http://localhost:3000" , "https://leafcity.vercel.app", "http://192.168.100.7:3000"})
    @DeleteMapping("/duration")
    @ResponseBody
    public ResponseEntity<String> deleteDuration(@RequestBody Duration duration) {
        durationService.deleteDuration(duration);
        return ResponseEntity.ok("Ok");
    }

    @CrossOrigin(origins = {"http://localhost:3000" , "https://leafcity.vercel.app", "http://192.168.100.7:3000"})
    @GetMapping("/durations")
    @ResponseBody
    public List<Duration> getDurations() {
        return durationService.findAll();
    }
    @CrossOrigin(origins = {"http://localhost:3000" , "https://leafcity.vercel.app", "http://192.168.100.7:3000"})
    @GetMapping("/categories")
    @ResponseBody
    public List<Category> getCategories() {
        return categoryService.findAll();
    }
}