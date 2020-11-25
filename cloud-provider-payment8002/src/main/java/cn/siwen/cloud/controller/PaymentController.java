package cn.siwen.cloud.controller;

import cn.siwen.cloud.entity.CommonResult;
import cn.siwen.cloud.entity.Payment;
import cn.siwen.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Value("${server.port}")
    String port;

    @PostMapping(value = "/payment/add")
    public CommonResult addPayment(@RequestBody Payment payment){
        int result=paymentService.addPayment(payment);
        System.out.println("****************"+result);
        if(result>0){
            return new CommonResult(200,"success",payment);
        }else {
            return new CommonResult(888,"faild");
        }
    }
    @GetMapping(value = "/payment/find/{id}")
    public CommonResult findPayment(@PathVariable long id){
        Payment payment = paymentService.findPaymentById(id);
        System.out.println("****************"+payment);
        if(payment!=null){
            return new CommonResult(200,"success/查询ID:"+id,"端口号:"+port);
        }else {
            return new CommonResult(999,"faild");
        }
    }
}
