package ru.vortex.physics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.client.RestTemplate;
import ru.vortex.physics.model.payment.Payment;
import ru.vortex.physics.model.shop.Product;
import ru.vortex.physics.repository.PaymentRepository;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class PaymentService {
    @Autowired
    PaymentRepository paymentRepository;

    public List<Payment> getPayments(){
        return paymentRepository.findAll();
    }
    public Payment getPayment(String id){
        return paymentRepository.findById(id).orElseThrow();
    }
    public void savePayment(Payment payment){
        paymentRepository.save(payment);
    }

    public Payment createPayment(Payment payment) {

        RestTemplate restTemplate = new RestTemplate();
        String shopId = System.getenv("SHOP_ID");
        String secretKey = System.getenv("SECRET_KEY");
        String IdempotenceKey = getRandomString();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        String auth = shopId + ":" + secretKey;
        byte[] encodedAuth = Base64Utils.encode(auth.getBytes(StandardCharsets.UTF_8));
        String authHeader = "Basic " + new String(encodedAuth);
        headers.set("Authorization", authHeader);
        headers.set("Idempotence-Key",IdempotenceKey);

        HttpEntity<Payment> request = new HttpEntity<>(payment, headers);
        Payment newPayment = restTemplate.postForObject("https://api.yookassa.ru/v3/payments", request, Payment.class);
        if(newPayment != null) {
            paymentRepository.save(newPayment);
        }
        return newPayment;
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
