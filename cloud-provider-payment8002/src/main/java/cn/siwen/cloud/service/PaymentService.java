package cn.siwen.cloud.service;

import cn.siwen.cloud.entity.Payment;

public interface PaymentService {

    public int addPayment(Payment payment);

    public Payment findPaymentById(Long id);
}
