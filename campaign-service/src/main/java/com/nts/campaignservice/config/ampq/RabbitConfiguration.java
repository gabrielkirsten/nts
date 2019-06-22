package com.nts.campaignservice.config.ampq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableRabbit
@Configuration
public class RabbitConfiguration {

    private final AmpqConfigurationProperties ampqProperties;

    @Autowired
    public RabbitConfiguration(AmpqConfigurationProperties ampqProperties) {
        this.ampqProperties = ampqProperties;
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory(ampqProperties.getHostname(), ampqProperties.getPort());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    @Bean
    public Queue myQueue() {
        return new Queue(ampqProperties.getQueue());
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(ampqProperties.getExchange());
    }

}
