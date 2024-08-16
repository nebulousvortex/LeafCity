package ru.vortex.physics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vortex.physics.model.payment.Payment;
import ru.vortex.physics.model.shop.Product;

public interface PaymentRepository extends JpaRepository<Payment, String> {

}
