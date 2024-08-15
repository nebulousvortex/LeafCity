package ru.vortex.physics.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.client.RestTemplate;
import ru.vortex.physics.model.payment.Payment;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Random;

@Service
public class PaymentService {

    public Payment createPayment(Payment payment) {
        RestTemplate restTemplate = new RestTemplate();
        String shopId = System.getenv("SHOP_ID");
        String secretKey = System.getenv("SECRET_KEY");
        String IdempotenceKey = getRandomString();
        //String IdempotenceKey = "LOCAL";
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

    private String getRandomString(){
        int leftLimit = 97;
        int rightLimit = 122;
        int targetStringLength = 8;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }
}
