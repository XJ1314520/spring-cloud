package cn.siwen.cloud.controller;

import ch.qos.logback.classic.Logger;
import cn.siwen.cloud.service.HystrixPaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
public class HystrixPaymentController {
    @Resource
    HystrixPaymentService hystrixPaymentService;

    @GetMapping(value = "/payment/hystrix/ok/{id}")
    public String paymentInfo_Ok(@PathVariable Integer id){
        return hystrixPaymentService.paymentInfo_Ok(id);
    }

    //timeout超时
    @GetMapping(value = "/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable Integer id){
        return hystrixPaymentService.paymentInfo_TimeOut(id);
    }
    //服务熔断
    @GetMapping(value = "/payment/hystrix/circuit/{id}")
    public String paymentInfo(@PathVariable("id") Integer id){
        return hystrixPaymentService.paymentCircuitBreaker(id);
    }
}
