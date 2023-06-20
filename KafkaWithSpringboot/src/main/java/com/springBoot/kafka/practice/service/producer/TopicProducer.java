package com.springBoot.kafka.practice.service.producer;

import com.springBoot.kafka.practice.entity.Topic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class TopicProducer {
    private static  final Logger LOGGER = LoggerFactory.getLogger(TopicProducer.class);

    private KafkaTemplate<String , Topic> kafkaTemplate;

    public TopicProducer(KafkaTemplate<String, Topic> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Topic data){
        LOGGER.info("Message sent  -> %s" , data.toString());
        Message message = MessageBuilder.withPayload(data)
                .setHeader(KafkaHeaders.TOPIC , "my_topic")
                .build();
        CompletableFuture<SendResult<String, Topic>> future = kafkaTemplate.send(message);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                LOGGER.info("Sent message=[" + message +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                LOGGER.info("Unable to send message=[" +
                        message + "] due to : " + ex.getMessage());
            }
        });
    }

}
