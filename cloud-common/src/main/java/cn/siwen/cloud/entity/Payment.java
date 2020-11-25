package cn.siwen.cloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor  //自动生成有参构造
@NoArgsConstructor   //无参构造
public class Payment {
    private long id;
    private String serial;
}
