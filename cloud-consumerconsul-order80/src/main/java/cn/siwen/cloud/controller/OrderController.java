package cn.siwen.cloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class OrderController {
    private static final String INVOME_URL="http://cloud-provider-payment";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/consume/payment/consul")
    public String payment(){
        String result=restTemplate.getForObject(INVOME_URL+"/payment/consul/",String.class);
        return result;
    }

}
