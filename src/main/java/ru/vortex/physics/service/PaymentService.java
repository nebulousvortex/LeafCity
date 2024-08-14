package ru.vortex.physics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.client.RestTemplate;
import ru.vortex.physics.model.Payment;

import java.nio.charset.StandardCharsets;
import java.util.Collections;

@Service
public class PaymentService {

    public Payment createPayment(Payment payment) {
        RestTemplate restTemplate = new RestTemplate();
        // Замена "YOUR_SHOP_ID" и "YOUR_SECRET_KEY" на ваши данные
        String shopId = "439925";
        String secretKey = "test_yFKZnWCR8VIo_DUOZhM0748O4mrCP4ftFPNqcS4vTZ8";
        String IdempotenceKey = "keykeykock";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        // Формирование Basic Auth
        String auth = shopId + ":" + secretKey;
        byte[] encodedAuth = Base64Utils.encode(auth.getBytes(StandardCharsets.UTF_8));
        String authHeader = "Basic " + new String(encodedAuth);
        headers.set("Authorization", authHeader);
        headers.set("Idempotence-Key",IdempotenceKey);

        HttpEntity<Payment> request = new HttpEntity<>(payment, headers);

        Payment response = restTemplate.postForObject("https://api.yookassa.ru/v3/payments", request, Payment.class);

        return response;
    }
}
