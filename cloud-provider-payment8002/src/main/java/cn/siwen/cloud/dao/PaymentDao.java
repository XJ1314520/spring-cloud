package cn.siwen.cloud.dao;

import cn.siwen.cloud.entity.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {
    /*
    增加一笔支付
     */
    public int addPayment(Payment payment);

    public Payment findPaymentById(@Param("id") long id);
}
