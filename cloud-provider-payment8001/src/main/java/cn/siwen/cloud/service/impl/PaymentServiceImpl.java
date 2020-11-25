package cn.siwen.cloud.service.impl;

import cn.siwen.cloud.dao.PaymentDao;
import cn.siwen.cloud.entity.Payment;
import cn.siwen.cloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentDao paymentDao;
    @Override
    public int addPayment(Payment payment) {
        return paymentDao.addPayment(payment);
    }

    @Override
    public Payment findPaymentById(Long id) {
        return paymentDao.findPaymentById(id);
    }
}
