package com.tensquare.rabbit.customer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "it3")
public class Customer3 {
    @RabbitHandler
    public void getMsg(String msg){
        System.out.println("it3："+msg);
    }


}
