package ru.vortex.physics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vortex.physics.model.payment.Webhook;
import ru.vortex.physics.service.PaymentService;


@RestController()
@RequestMapping("/webhook")
public class WebhookController {

    @Autowired
    PaymentService paymentService;

    @PostMapping("/getNotify")
    @ResponseBody
    public ResponseEntity<String> getNotify(@RequestBody Webhook webhook) {
        System.out.println("МБ НУЛЛ?");
        if(webhook.getObject() != null){
            System.out.println("ВСЕ ТАКИ НЕ НУЛЛ");
            paymentService.savePayment(webhook.getObject());
            System.out.println(webhook.getObject().getDescription());
            System.out.println(webhook.getObject().getStatus());
        }
        return ResponseEntity.ok("Ok");
    }
}
