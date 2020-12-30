package com.tensquare.rabbit.customer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "itcast")
public class Customer1 {
    @RabbitHandler
    public void getMsg(String msg){
        System.out.println("333直接模式消费模式："+msg);
    }


}
