package ru.vortex.physics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vortex.physics.model.payment.Amount;
import ru.vortex.physics.model.payment.Confirmation;
import ru.vortex.physics.model.payment.Payment;
import ru.vortex.physics.model.payment.PaymentMeta;
import ru.vortex.physics.model.request.UserProductRequest;
import ru.vortex.physics.model.shop.Product;
import ru.vortex.physics.service.PaymentService;
import ru.vortex.physics.service.ShopService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

@RestController()
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private ShopService shopService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/getRedirectPayment")
    @ResponseBody
    public ResponseEntity<Map<String, String>> createPaymentRedirect(@RequestBody UserProductRequest userProductRequest) {
        Payment newPay = new Payment();
        Product product = shopService.getProductById(userProductRequest.getProductId());

        newPay.setAmount(new Amount(Float.toString(product.getPrice()), "RUB"));
        newPay.setDescription(product.getName());
        newPay.setCapture(true);
        newPay.setMetadata(new PaymentMeta(userProductRequest.getUsername(), product.getId(), product.getName()));
        newPay.setConfirmation(new Confirmation("redirect", "", "http://localhost:3000/shop"));
        newPay = paymentService.createPayment(newPay);

        Map<String, String> response = new HashMap<>();
        response.put("confirmation_url", newPay.getConfirmation().getConfirmation_url());
        return ResponseEntity.ok(response);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getPayments")
    @ResponseBody
    public List<Payment> getPayments() {
        return paymentService.getPayments();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getPayment")
    @ResponseBody
    public Payment getPayment(@RequestParam  String id) {
        return paymentService.getPayment(id);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getLastPayments")
    @ResponseBody
    public List<Map<String, Object>> getLastPayments() {
        List<Payment> allPayments = paymentService.getPayments();
        allPayments.sort(Comparator.comparing(Payment::getCreated_at).reversed());
        List<Payment> lastPayments = allPayments.subList(0, Math.min(10, allPayments.size()));

        List<Map<String, Object>> jsonResults = new ArrayList<>();

        for (Payment payment : lastPayments) {
            Map<String, Object> paymentJson = new HashMap<>();

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date createdDate = null;
            try {
                createdDate = formatter.parse(payment.getCreated_at());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Long diffInMillis = System.currentTimeMillis() - createdDate.getTime();
            Long diffDays = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);
            String daysAgo ="";
            if (diffDays==0){
                daysAgo = "сегодня!";
            }else if (diffDays.toString().endsWith("1")){
                daysAgo = diffDays + "день назад";
            }
            else if (diffDays.toString().endsWith("2")||diffDays.toString().endsWith("3")||diffDays.toString().endsWith("4")){
                daysAgo = diffDays + "дня назад";
            }else{
                daysAgo = diffDays + "дней назад";
            }

            paymentJson.put("daysAgo", daysAgo);
            paymentJson.put("username", payment.getMetadata().getUsername());
            paymentJson.put("product_id", payment.getMetadata().getProductId());
            jsonResults.add(paymentJson);
        }

        return jsonResults;
    }
}
