package cn.siwen.cloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.cloud.commons.util.IdUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
@DefaultProperties(defaultFallback = "payment_global_fallback_method")//定义全局降级方法
public class HystrixPaymentService {
    //成功
    public String paymentInfo_Ok(Integer id){
        return "线程池:"+Thread.currentThread().getName()+"  paymentInfo_Ok,id:   "+id+"\t"+"成功";
    }

    //timeout超时
    @HystrixCommand(fallbackMethod = "HystrixPaymentService_fallBack",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })//自定义降级方法
    //@HystrixCommand//使用全局降级方法
    public String paymentInfo_TimeOut(Integer id){
        int timeNumber=3;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "线程池:  "+Thread.currentThread().getName()+"   paymentInfo_TimeOut,id:   "+id+"\t"+"延时"+timeNumber+"秒";
    }
    /*
    服务降级之后会执行该方法
     */
    public String HystrixPaymentService_fallBack(Integer id){
        return "线程池:"+Thread.currentThread().getName()+"    系统繁忙，请稍后再试，id:"+id+"\t"+"兜底方法";
    }
    //全局降级方法
    public String payment_global_fallback_method(){
        return "降级了!!!!";
    }

    //服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),//是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),//时间范围
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")//失败率达到多少后跳闸，此处为60%
            //(上面配置意思是：10秒钟内，10次请求失败率60%就不让用了)
            //具体需要配置哪些参数，在HystrixCommandProperties.java类中
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if (id<0){
            throw new RuntimeException("*******id 不能负数");
        }
        String serialNumber= IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"\t"+"调用成功，流水号："+serialNumber;
    }
    //服务降级
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "id不能负数，请稍后再试,   id:"+id;
    }
}
