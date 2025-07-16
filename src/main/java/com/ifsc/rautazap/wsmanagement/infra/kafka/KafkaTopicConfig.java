package com.ifsc.rautazap.wsmanagement.infra.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@Slf4j
public class KafkaTopicConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public NewTopic userOnlineTopic() {
        return TopicBuilder
                .name("user-online")
                .partitions(1)
                .replicas(1)
                .config(TopicConfig.RETENTION_MS_CONFIG, "604800000")
                .config(TopicConfig.CLEANUP_POLICY_CONFIG, "delete")
                .build();
    }

    @Bean
    public NewTopic saveMessageTopic() {
        return TopicBuilder
                .name("save-message")
                .partitions(3)
                .replicas(1)
                .config(TopicConfig.RETENTION_MS_CONFIG, "604800000")
                .config(TopicConfig.CLEANUP_POLICY_CONFIG, "delete")
                .build();
    }

}
