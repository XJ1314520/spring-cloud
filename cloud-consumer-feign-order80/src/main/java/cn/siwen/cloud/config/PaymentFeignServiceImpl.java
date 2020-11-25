package cn.siwen.cloud.config;

import cn.siwen.cloud.entity.CommonResult;
import cn.siwen.cloud.entity.Payment;

public class PaymentFeignServiceImpl  implements PaymentFeignService{
    @Override
    public CommonResult getPaymentById(Long id) {
        return new CommonResult(404,"OpenFeign兜底方法",new Payment(id,"errorSerial"));
    }
}
