package com.nts.campaignservice.config.ampq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

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
    public Queue myQueue() {
            return new Queue(ampqProperties.getQueue(), false, false, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(ampqProperties.getExchange());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json()
                .modules(new JavaTimeModule())
                .dateFormat(new StdDateFormat())
                .build();
        return new Jackson2JsonMessageConverter(objectMapper);
    }

}
