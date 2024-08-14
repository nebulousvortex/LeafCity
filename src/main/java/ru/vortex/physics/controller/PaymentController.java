package ru.vortex.physics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.vortex.physics.model.Amount;
import ru.vortex.physics.model.Confirmation;
import ru.vortex.physics.model.Payment;
import ru.vortex.physics.service.PaymentService;

@Controller
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/payments")
    @ResponseBody
    public Payment createPayment() {
        Payment newPay = new Payment();
        newPay.setAmount(new Amount("1", "RUB"));
        newPay.setDescription("Тестовое описание");
        newPay.setCapture(true);
        newPay.setConfirmation(new Confirmation("embedded"));
        //newPay.setPaymentMethodData("");

        newPay = paymentService.createPayment(newPay);
        return newPay;
    }
}
