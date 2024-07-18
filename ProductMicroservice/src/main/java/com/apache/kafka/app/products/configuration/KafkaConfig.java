package com.apache.kafka.app.products.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.Map;

@Configuration
public class KafkaConfig {

    @Bean
    NewTopic createTopic(){
        return TopicBuilder.name("product-created-events-topic")
                .partitions(3)
                .replicas(3)
                // Minimum number of replicas to acknowledge for success write
                .configs(Map.of("min.insync.replicas", "2"))
                .build();
    }
}

//./bin/kafka-server-start.sh config/kraft/server2.properties
//./bin/kafka-server-start.sh config/kraft/server3.properties
//./bin/kafka-server-start.sh config/kraft/server1.properties