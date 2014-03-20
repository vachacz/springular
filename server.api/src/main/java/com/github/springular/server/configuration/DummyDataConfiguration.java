package com.github.springular.server.configuration;

import com.github.springular.server.configuration.db.DummyDataCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DummyDataConfiguration {

    @Bean
    public DummyDataCreator dummyDataCreator() {
        return new DummyDataCreator();
    }

}
