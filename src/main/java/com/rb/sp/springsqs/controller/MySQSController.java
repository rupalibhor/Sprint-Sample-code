package com.rb.sp.springsqs.controller;

import org.springframework.web.bind.annotation.RestController;

import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequiredArgsConstructor
public class MySQSController {
    
 private final SqsTemplate sqsTemplate;

     @Value("${spring.cloud.aws.sqs.endpoint}")
    private String endpoint;

    @PostMapping("/my/addrb")
    public ResponseEntity<String> addRB(@RequestParam String name) {
         return ResponseEntity.ok("added sucessfully");
    }

    @GetMapping("/send/{message}/{groupId}/{deDupId}")
    public String send(@PathVariable(value = "message") String message,
                     @PathVariable(value = "groupId") String groupId,
                     @PathVariable(value = "deDupId") String deDupId){
       Message<String> payload = MessageBuilder.withPayload(message)
               .setHeader("message-group-id", groupId)
               .setHeader("message-deduplication-id", deDupId)
               .build();
       sqsTemplate.send(endpoint, payload);

       return "Message sent to SQS FIFO queue: " + endpoint;
       
    }
    
    
   
}
