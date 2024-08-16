package ru.vortex.physics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vortex.physics.model.payment.Amount;
import ru.vortex.physics.model.payment.Confirmation;
import ru.vortex.physics.model.payment.Payment;
import ru.vortex.physics.model.request.UserProductRequest;
import ru.vortex.physics.model.shop.Product;
import ru.vortex.physics.service.PaymentService;
import ru.vortex.physics.service.ShopService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private ShopService shopService;

    @PostMapping("/getEmbeddedPayment")
    @ResponseBody
    public Payment createEmbeddedPayment() {
        Payment newPay = new Payment();
        newPay.setAmount(new Amount("1", "RUB"));
        newPay.setDescription("Тестовое описание для embedded");
        newPay.setCapture(true);
        newPay.setConfirmation(new Confirmation("embedded"));

        newPay = paymentService.createPayment(newPay);
        return newPay;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/getRedirectPayment")
    @ResponseBody
    public ResponseEntity<Map<String, String>> createPaymentRedirect(@RequestBody UserProductRequest userProductRequest) {
        Payment newPay = new Payment();

        Product product = shopService.getProductById(userProductRequest.getProductId());
        newPay.setAmount(new Amount(Float.toString(product.getPrice()), "RUB"));
        newPay.setDescription(product.getName());
        newPay.setCapture(true);
        newPay.setConfirmation(new Confirmation("redirect", "", "http://localhost:3000/shop"));
        newPay = paymentService.createPayment(newPay);

        Map<String, String> response = new HashMap<>();
        response.put("confirmation_url", newPay.getConfirmation().getConfirmation_url());
        return ResponseEntity.ok(response);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getPayments")
    @ResponseBody
    public List<Payment> getPayments() {
        return paymentService.getPayments();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getPayment")
    @ResponseBody
    public Payment getPayment(@RequestParam  String id) {
        return paymentService.getPayment(id);
    }
}
