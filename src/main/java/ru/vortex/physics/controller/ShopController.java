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
@CrossOrigin(origins = {"http://localhost:3000" , "https://leafcity.vercel.app", "https://leafcity.ru"})
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    private ShopService shopService;
    @Autowired
    private DurationService durationService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/products")
    @ResponseBody
    public List<Product> getProducts() {
        return shopService.getProducts();
    }
    @GetMapping("/product")
    @ResponseBody
    public Product getProduct(@RequestParam  Long id) {
        return shopService.getProductById(id);
    }
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
    @DeleteMapping("/product")
    @ResponseBody
    public ResponseEntity<String> deleteProduct(@RequestBody Product product) {
        shopService.deleteProduct(product);
        return ResponseEntity.ok("Ok");
    }
    @PatchMapping("/product")
    @ResponseBody
    public ResponseEntity<String> patchProduct(@RequestBody Product product) {
        shopService.patchProduct(product);
        return ResponseEntity.ok("Ok");
    }
    @PostMapping("/category")
    @ResponseBody
    public ResponseEntity<String> createCategory(@RequestBody Category category) {
        categoryService.createCategory(category);
        return ResponseEntity.ok("Ok");
    }
    @DeleteMapping("/category")
    @ResponseBody
    public ResponseEntity<String> deleteCategory(@RequestBody Category category) {
        categoryService.deleteCategory(category);
        return ResponseEntity.ok("Ok");
    }
    @PostMapping("/duration")
    @ResponseBody
    public ResponseEntity<String> createDuration(@RequestBody Duration duration) {
        durationService.createDuration(duration);
        return ResponseEntity.ok("Ok");
    }
    @DeleteMapping("/duration")
    @ResponseBody
    public ResponseEntity<String> deleteDuration(@RequestBody Duration duration) {
        durationService.deleteDuration(duration);
        return ResponseEntity.ok("Ok");
    }
    @GetMapping("/durations")
    @ResponseBody
    public List<Duration> getDurations() {
        return durationService.findAll();
    }
    @GetMapping("/categories")
    @ResponseBody
    public List<Category> getCategories() {
        return categoryService.findAll();
    }
}