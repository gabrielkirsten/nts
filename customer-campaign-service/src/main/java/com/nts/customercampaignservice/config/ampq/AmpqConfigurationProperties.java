package com.nts.customercampaignservice.config.ampq;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("ampq")
public class AmpqConfigurationProperties {

    private String hostname;
    private Integer port;
    private String exchange;
    private String queue;

}
