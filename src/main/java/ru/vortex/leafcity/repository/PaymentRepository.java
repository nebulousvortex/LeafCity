package ru.vortex.leafcity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.vortex.leafcity.model.payment.Payment;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, String> {
        List<Payment> findByStatus(String status);
        @Query("SELECT COALESCE(MAX(p.shortId), 0) FROM Payment p")
        Long findMaxShortId();
}
