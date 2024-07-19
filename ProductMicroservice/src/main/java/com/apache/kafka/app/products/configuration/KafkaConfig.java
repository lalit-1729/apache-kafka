package com.apache.kafka.app.products.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.Map;

@Configuration
public class KafkaConfig {

    @Value("${product.created.topic.name}")
    String productCreatedTopicName;

    @Bean
    NewTopic createTopic(){
        return TopicBuilder.name(productCreatedTopicName)
                .partitions(3)
                .replicas(3)
                // Minimum number of replicas to acknowledge for success write
                .configs(Map.of("min.insync.replicas", "3"))
                .build();
    }
}

