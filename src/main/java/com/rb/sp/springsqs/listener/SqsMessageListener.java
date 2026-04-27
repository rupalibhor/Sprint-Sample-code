package com.rb.sp.springsqs.listener;

import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import io.awspring.cloud.sqs.annotation.SqsListener;

@Component
public class SqsMessageListener {

    @SqsListener("${spring.cloud.aws.sqs.endpoint}")
    public void receiveMessage(Message<String> message) {

        String payload = message.getPayload();
        System.out.println("Received message: " + payload);

        // You can also access headers if needed
        message.getHeaders().forEach((key, value) -> {
            System.out.println("Header: " + key + " = " + value);
        });

    }
}
