package cn.siwen.cloud.controller;

import cn.siwen.cloud.config.HystrixOrderService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
public class HystrixOrderController {
    @Resource
    HystrixOrderService hystrixOrderService;
    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_Ok(@PathVariable("id") Integer id){
        return hystrixOrderService.paymentInfo_Ok(id);
    }


    @GetMapping("/payment/hystrix/timeout/{id}")
    @HystrixCommand(fallbackMethod = "HystrixTimeOutFallbackMethod",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")
    })
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id){
        return hystrixOrderService.paymentInfo_TimeOut(id);
    }
    //服务降级方法
    public String HystrixTimeOutFallbackMethod(@PathVariable("id") Integer id){
        return "系统繁忙!!!";
    }
}
