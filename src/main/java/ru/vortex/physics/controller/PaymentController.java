package ru.vortex.physics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vortex.physics.model.Amount;
import ru.vortex.physics.model.Confirmation;
import ru.vortex.physics.model.Payment;
import ru.vortex.physics.service.PaymentService;

import java.util.HashMap;
import java.util.Map;

@RestController()
@RequestMapping("/api/v1")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/getPayment")
    @ResponseBody
    public Payment createPayment() {
        Payment newPay = new Payment();
        newPay.setAmount(new Amount("1", "RUB"));
        newPay.setDescription("Тестовое описание");
        newPay.setCapture(true);
        newPay.setConfirmation(new Confirmation("embedded"));

        newPay = paymentService.createPayment(newPay);
        return newPay;
    }

    @PostMapping("/getToken")
    @ResponseBody
    public ResponseEntity<Map<String, String>> getConfirmationToken() {
        Payment newPay = new Payment();
        newPay.setAmount(new Amount("1", "RUB"));
        newPay.setDescription("Тестовое описание");
        newPay.setCapture(true);
        newPay.setConfirmation(new Confirmation("embedded"));

        newPay = paymentService.createPayment(newPay);
        Map<String, String> response = new HashMap<>();
        response.put("confirmation_token", newPay.getConfirmation().getConfirmation_token());

        return ResponseEntity.ok(response);
    }
}
