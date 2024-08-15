package ru.vortex.physics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.vortex.physics.model.shop.Product;
import ru.vortex.physics.service.PaymentService;
import ru.vortex.physics.service.ShopService;

import java.util.ArrayList;

@RestController()
@RequestMapping("/api/v1/shop")
public class ShopController {
    @Autowired
    private ShopService shopService;

    @GetMapping("/getProducts")
    @ResponseBody
    public ArrayList<Product> getProducts() {
        return shopService.getProducts();
    }
}
