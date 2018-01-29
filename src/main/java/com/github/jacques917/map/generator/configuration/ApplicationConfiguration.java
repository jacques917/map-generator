package com.github.jacques917.map.generator.configuration;

import com.google.common.eventbus.EventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public EventBus eventBus() {
        return new EventBus();
    }

}
