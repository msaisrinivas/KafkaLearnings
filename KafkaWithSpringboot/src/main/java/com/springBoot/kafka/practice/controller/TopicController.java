package com.springBoot.kafka.practice.controller;

import com.springBoot.kafka.practice.entity.Topic;
import com.springBoot.kafka.practice.service.producer.TopicProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gmail")
public class TopicController {
    @Autowired
    private TopicProducer topicProducer;


    @PostMapping("/publish")
    public ResponseEntity<String> publish(@RequestBody Topic topic) {
        topicProducer.sendMessage(topic);
        return ResponseEntity.ok("Message sent to the kafka topic of "+ topic.getId());
    }
}
