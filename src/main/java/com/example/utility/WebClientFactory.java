package com.example.utility;

import com.example.configuration.ServiceUrlConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class WebClientFactory {
    @Autowired
    private ServiceUrlConfiguration serviceUrlConfiguration;

    @Autowired
    WebClient.Builder webClientBuilder;
    public WebClient getUserUrl(){
        return webClientBuilder.baseUrl(serviceUrlConfiguration.getUser()).build();
    }
}
