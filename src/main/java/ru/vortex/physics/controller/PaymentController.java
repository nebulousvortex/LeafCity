package ru.vortex.physics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vortex.physics.model.payment.Amount;
import ru.vortex.physics.model.payment.Confirmation;
import ru.vortex.physics.model.payment.Payment;
import ru.vortex.physics.model.payment.PaymentMeta;
import ru.vortex.physics.model.request.UserProductRequest;
import ru.vortex.physics.model.shop.Product;
import ru.vortex.physics.service.PaymentService;
import ru.vortex.physics.service.ShopService;

import java.text.ParseException;
import java.util.*;

@RestController()
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private ShopService shopService;

    @CrossOrigin(origins = {"http://localhost:3000" , "https://leafcity.vercel.app", "http://192.168.100.7:3000"})
    @PostMapping("/getRedirectPayment")
    @ResponseBody
    public ResponseEntity<Map<String, String>> createPaymentRedirect(@RequestBody UserProductRequest userProductRequest) {
        Payment newPay = new Payment();
        Product product = shopService.getProductById(userProductRequest.getProductId());

        newPay.setAmount(new Amount(Float.toString(product.getPrice()), "RUB"));
        newPay.setDescription(product.getName());
        newPay.setCapture(true);
        newPay.setMetadata(new PaymentMeta(userProductRequest.getUsername(), product.getId(), product.getName()));
        newPay.setConfirmation(new Confirmation("redirect", "", userProductRequest.getRedirectUrl()));
        newPay = paymentService.createPayment(newPay);

        Map<String, String> response = new HashMap<>();
        response.put("confirmation_url", newPay.getConfirmation().getConfirmation_url());
        return ResponseEntity.ok(response);
    }
    @CrossOrigin(origins = {"http://localhost:3000" , "https://leafcity.vercel.app", "http://192.168.100.7:3000"})
    @GetMapping("/getPayments")
    @ResponseBody
    public List<Payment> getPayments() {
        return paymentService.getPayments();
    }

    @CrossOrigin(origins = {"http://localhost:3000" , "https://leafcity.vercel.app", "http://192.168.100.7:3000"})
    @GetMapping("/getPayment")
    @ResponseBody
    public Payment getPayment(@RequestParam  String id) {
        return paymentService.getPayment(id);
    }

    @CrossOrigin(origins = {"http://localhost:3000" , "https://leafcity.vercel.app", "http://192.168.100.7:3000"})
    @GetMapping("/getLastPayments")
    @ResponseBody
    public List<Map<String, Object>> getLastPayments() throws ParseException {
        return paymentService.getLastPayments();
    }
}
