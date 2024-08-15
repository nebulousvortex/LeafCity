package ru.vortex.physics.service;

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
        String shopId = System.getenv("SHOP_ID");
        String secretKey = System.getenv("SECRET_KEY");
        String IdempotenceKey = "keykeykock";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        String auth = shopId + ":" + secretKey;
        byte[] encodedAuth = Base64Utils.encode(auth.getBytes(StandardCharsets.UTF_8));
        String authHeader = "Basic " + new String(encodedAuth);
        headers.set("Authorization", authHeader);
        headers.set("Idempotence-Key",IdempotenceKey);

        HttpEntity<Payment> request = new HttpEntity<>(payment, headers);

        return restTemplate.postForObject("https://api.yookassa.ru/v3/payments", request, Payment.class);
    }
}
