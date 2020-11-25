package cn.siwen.cloud.controller;

import cn.siwen.cloud.entity.CommonResult;
import cn.siwen.cloud.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {
    private static final String PAYMENTURL="http://PAYMENT";
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/consume/order/{id}")
    public CommonResult orderfind(@PathVariable long id){
        return restTemplate.getForObject(PAYMENTURL+"/payment/find/"+id,CommonResult.class,id);
    }

    public CommonResult orderCreate(Payment payment){
        return restTemplate.postForObject(PAYMENTURL+"/payment/add",payment,CommonResult.class,payment);
    }
}
