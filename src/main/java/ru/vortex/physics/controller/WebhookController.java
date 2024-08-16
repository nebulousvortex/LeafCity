package ru.vortex.physics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vortex.physics.model.payment.Confirmation;
import ru.vortex.physics.model.payment.Payment;
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
        if(webhook.getObject() != null){
            Payment payment =  webhook.getObject();
            payment.getConfirmation().setEnforce(true);
            paymentService.savePayment(payment);
        }
        return ResponseEntity.ok("Ok");
    }
}
