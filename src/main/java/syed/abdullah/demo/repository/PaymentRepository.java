package syed.abdullah.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import syed.abdullah.demo.entity.Payment;
import syed.abdullah.demo.entity.PaymentId;

public interface PaymentRepository extends JpaRepository<Payment, PaymentId>, JpaSpecificationExecutor<Payment> {
}