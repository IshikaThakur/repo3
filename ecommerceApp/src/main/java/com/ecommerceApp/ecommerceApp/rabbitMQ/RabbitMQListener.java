package com.ecommerceApp.ecommerceApp.rabbitMQ;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import javax.management.Notification;

@Component
public class RabbitMQListener {
     Object productObject;
    //@RabbitListener(queues="${rabbitmq.queueName}")
    public void listen(byte[] message) {
        String msg = new String(message);
        Notification not = new Gson().fromJson(msg, Notification.class);
        System.out.println("Received a new notification...");
        System.out.println(not.toString());
    }

    public Object getProductObject() {
        return productObject;
    }

}

