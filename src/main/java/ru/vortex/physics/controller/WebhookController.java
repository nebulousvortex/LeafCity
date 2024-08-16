package ru.vortex.physics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vortex.physics.model.payment.Payment;
import ru.vortex.physics.model.payment.Webhook;
import ru.vortex.physics.model.shop.Product;
import ru.vortex.physics.repository.PaymentRepository;
import ru.vortex.physics.service.PaymentService;

import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/webhook")
public class WebhookController {

    @Autowired
    PaymentService paymentService;

    @PostMapping("/getNotify")
    @ResponseBody
    public ResponseEntity<String> getNotify(@RequestBody Webhook webhook) {
        if(webhook.getPayment() != null){
            paymentService.savePayment(webhook.getPayment());
        }
        return ResponseEntity.ok("Ok");
    }
}
