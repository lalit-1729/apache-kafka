package com.apache.kafka.app.products.serviceImpl;

import com.apache.kafka.app.products.dtos.CreateProductRequest;
import com.apache.kafka.app.products.event.ProductCreatedEvent;
import com.apache.kafka.app.products.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Value("${product.created.topic.name}")
    String productCreatedTopicName;

    @Autowired
    KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;

    @Override
    public String createProduct(CreateProductRequest request) throws Exception {
        String productId = UUID.randomUUID().toString();

        // TODO: Logic to persist data in the database
        ProductCreatedEvent productCreatedEvent = new
                ProductCreatedEvent(productId, request.getTitle(), request.getPrice(), request.getQuantity());

        // This sends the message asynchronously
        /*
        CompletableFuture<SendResult<String, ProductCreatedEvent>> future =
                kafkaTemplate.send(productCreatedTopicName, productId, productCreatedEvent);
        future.whenComplete((result, exception) ->{
           if(exception != null){
               LOGGER.error("The event failed to register {}", exception.getMessage());
           }else {
                LOGGER.info("The event is register {}", result.getRecordMetadata());
           }
        });
        */

        // This sends the message synchronously
        // here we wait only for the acknowledgement from the leader broker

        SendResult<String, ProductCreatedEvent> result =
            kafkaTemplate.send(productCreatedTopicName, productId, productCreatedEvent).get();

        LOGGER.info("Partition : {}", result.getRecordMetadata().partition());
        LOGGER.info("Topic : {}", result.getRecordMetadata().topic());
        LOGGER.info("Offset : {}", result.getRecordMetadata().offset());

        LOGGER.info("Returning Product Id");
        return productId;
    }
}
