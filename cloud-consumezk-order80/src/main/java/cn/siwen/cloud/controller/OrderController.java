package cn.siwen.cloud.controller;

import cn.siwen.cloud.entity.CommonResult;
import cn.siwen.cloud.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class OrderController {
    //使用服务名的方式调用
    private static final String INVOME_URL="http://cloud-provider-payment";
    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/consume/payment/zk")
    public String payment(){
        String result=restTemplate.getForObject(INVOME_URL+"/payment/zk/",String.class);
        return result;
    }

}
