package ru.vortex.leafcity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vortex.leafcity.model.payment.*;
import ru.vortex.leafcity.model.request.UserProductRequest;
import ru.vortex.leafcity.model.shop.Product;
import ru.vortex.leafcity.model.shop.Promocode;
import ru.vortex.leafcity.service.PaymentService;
import ru.vortex.leafcity.service.PromocodeService;
import ru.vortex.leafcity.service.ShopService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.*;

@RestController()
@CrossOrigin(origins = {"http://localhost:3000" , "https://leafcity.vercel.app", "https://leafcity.ru", "http://91.233.43.231"})
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private PromocodeService promocodeService;


    @PostMapping("/getRedirectPayment")
    @ResponseBody
    public ResponseEntity<?> createPaymentRedirect(@RequestBody UserProductRequest userProductRequest) {
        Payment newPay = new Payment();
        Product product = shopService.getProductById(userProductRequest.getProductId());

        if (product == null) {
            return ResponseEntity.badRequest().body("Продукт не найден!");
        }

        // Получаем скидку по промокоду
        float promocodeDiscount = promocodeService.getDiscountByCode(userProductRequest.getPromoCode());
        if (promocodeDiscount < 0) {
            return ResponseEntity.badRequest().body("Некорректный промокод!");
        }

        Long shortId = paymentService.getNextShortId();
        newPay.setShortId(shortId);

        // Рассчитываем сумму с учетом скидки
        BigDecimal realPrice = BigDecimal.valueOf(product.getRealPrice());
        BigDecimal totalPrice = realPrice.multiply(BigDecimal.valueOf(1 - promocodeDiscount)).multiply(BigDecimal.valueOf(userProductRequest.getCount()));
        Amount amount = new Amount(totalPrice.setScale(2, RoundingMode.HALF_UP).toString(), "RUB");

        // Формируем чек и метаданные платежа
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item(product.getName(), amount, 2, 1, "another", "commodity", "full_payment"));

        newPay.setReceipt(new Receipt(items, new Customer(userProductRequest.getEmail())));
        newPay.setAmount(amount);
        newPay.setDescription("Платеж #" + shortId + " в магазине leafcity.ru/shop за заказ товара " + product.getName() + " пользователю " + userProductRequest.getUsername());
        newPay.setCapture(true);
        newPay.setMetadata(new PaymentMeta(userProductRequest.getUsername(), product.getId(), product.getName()));
        newPay.setConfirmation(new Confirmation("redirect", "", userProductRequest.getRedirectUrl()));

        // Создаем платеж
        newPay = paymentService.createPayment(newPay);

        Map<String, String> response = new HashMap<>();
        response.put("confirmation_url", newPay.getConfirmation().getConfirmation_url());
        return ResponseEntity.ok(response);
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
