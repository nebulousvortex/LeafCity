package ru.vortex.leafcity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vortex.leafcity.model.payment.Payment;
import ru.vortex.leafcity.model.payment.Webhook;
import ru.vortex.leafcity.service.PaymentService;
import ru.vortex.leafcity.service.RconService;
import ru.vortex.leafcity.service.ShopService;
import ru.vortex.leafcity.utils.ex.AuthenticationException;

import java.io.IOException;


@RestController()
@RequestMapping("/webhook")
public class WebhookController {

    @Autowired
    PaymentService paymentService;
    @Autowired
    ShopService shopService;
    @Autowired
    RconService rconService;

    @PostMapping("/getNotify")
    @ResponseBody
    public ResponseEntity<String> getNotify(@RequestBody Webhook webhook) throws AuthenticationException, IOException {
        if(webhook.getObject() != null){
            Payment payment =  webhook.getObject();
            Payment existingPayment = paymentService.getPayment(webhook.getObject().getId());
            existingPayment.setStatus(payment.getStatus());
            paymentService.savePayment(existingPayment);
            if(payment.getStatus().equals("succeeded")){
                rconService.sendCommand(payment.getMetadata().getUsername(), shopService.getProductById(payment.getMetadata().getProductId()));
            }
        }
        return ResponseEntity.ok("Ok");
    }
}
