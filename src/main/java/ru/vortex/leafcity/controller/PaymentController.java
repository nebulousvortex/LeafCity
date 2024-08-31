package ru.vortex.leafcity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vortex.leafcity.model.payment.*;
import ru.vortex.leafcity.model.request.UserProductRequest;
import ru.vortex.leafcity.model.shop.Product;
import ru.vortex.leafcity.service.PaymentService;
import ru.vortex.leafcity.service.ShopService;

import java.text.ParseException;
import java.util.*;

@RestController()
@CrossOrigin(origins = {"http://localhost:3000" , "https://leafcity.vercel.app", "https://leafcity.ru"})
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private ShopService shopService;


    @PostMapping("/getRedirectPayment")
    @ResponseBody
    public ResponseEntity<?> createPaymentRedirect(@RequestBody UserProductRequest userProductRequest) {
        Payment newPay = new Payment();
        Product product = shopService.getProductById(userProductRequest.getProductId());
        if(product != null) {
            Amount amount = new Amount(Float.toString(product.getRealPrice()), "RUB");
            ArrayList<Item> items = new ArrayList<Item>();
            items.add(new Item(product.getName(), amount, 2, 1, "another"));
            newPay.setReceipt(new Receipt(items, new Customer("vortexofnebula@mail.ru")));
            newPay.setAmount(amount);
            newPay.setDescription(product.getName());
            newPay.setCapture(true);
            newPay.setMetadata(new PaymentMeta(userProductRequest.getUsername(), product.getId(), product.getName()));
            newPay.setConfirmation(new Confirmation("redirect", "", userProductRequest.getRedirectUrl()));
            newPay = paymentService.createPayment(newPay);

            Map<String, String> response = new HashMap<>();
            response.put("confirmation_url", newPay.getConfirmation().getConfirmation_url());
            return ResponseEntity.ok(response);
        }
        return  ResponseEntity.badRequest().body("продукт не найден!");
    }
    @GetMapping("/getPayments")
    @ResponseBody
    public List<Payment> getPayments() {
        return paymentService.getPayments();
    }

    @GetMapping("/getPayment")
    @ResponseBody
    public Payment getPayment(@RequestParam  String id) {
        return paymentService.getPayment(id);
    }

    @GetMapping("/getLastPayments")
    @ResponseBody
    public List<Map<String, Object>> getLastPayments() throws ParseException {
        return paymentService.getLastPayments();
    }
}
