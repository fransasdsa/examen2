// Archivo: src/main/java/com/upeu/paymentservice/repository/PaymentRepository.java
package com.upeu.paymentservice.repository;

import com.upeu.paymentservice.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByPaymentCode(String paymentCode);
}
