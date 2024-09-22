// Archivo: src/main/java/com/upeu/paymentservice/service/PaymentService.java
package com.upeu.paymentservice.service;

import com.upeu.paymentservice.entity.Payment;
import com.upeu.paymentservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Optional<Payment> getPaymentById(Long id) {
        return paymentRepository.findById(id);
    }

    public Optional<Payment> getPaymentByPaymentCode(String paymentCode) {
        return paymentRepository.findByPaymentCode(paymentCode);
    }

    public Payment createPayment(Payment payment) {
        payment.setTimestamp(LocalDateTime.now());
        return paymentRepository.save(payment);
    }

    public Payment updatePayment(Long id, Payment paymentDetails) {
        return paymentRepository.findById(id).map(payment -> {
            payment.setPaymentCode(paymentDetails.getPaymentCode());
            payment.setAmount(paymentDetails.getAmount());
            payment.setCurrency(paymentDetails.getCurrency());
            payment.setStatus(paymentDetails.getStatus());
            payment.setStudentCode(paymentDetails.getStudentCode());
            // No actualizar timestamp en actualización
            return paymentRepository.save(payment);
        }).orElseGet(() -> {
            paymentDetails.setId(id);
            paymentDetails.setTimestamp(LocalDateTime.now());
            return paymentRepository.save(paymentDetails);
        });
    }

    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }
}
