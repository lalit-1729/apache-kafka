package com.kafka.emailservice.EmailNotificationService.handler;

import com.apache.kafka.Shared.ProductCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(topics = {"product-created-events-topic"})
public class ProductCreatedEventHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @KafkaHandler
    public void handleProductCreatedEvent(ProductCreatedEvent productCreatedEvent){
        // TODO: trigger mail to user for product created notification
        LOGGER.info("The message us received  : {}", productCreatedEvent);
    }
}
