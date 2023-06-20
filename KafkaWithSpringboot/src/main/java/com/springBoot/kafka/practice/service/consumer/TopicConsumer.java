package com.springBoot.kafka.practice.service.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TopicConsumer {
    private static  final Logger LOGGER = LoggerFactory.getLogger(TopicConsumer.class);

    @KafkaListener(topics = "my_topic", groupId = "default")
    public void consume(ConsumerRecord<String, String> data){
        LOGGER.info(String.format("Topic  received  -> %s" , data.value()));
    }
}
