package ru.vortex.leafcity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vortex.leafcity.model.payment.Payment;

public interface PaymentRepository extends JpaRepository<Payment, String> {

}
