package org.jacobsen.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

/**
 * Created by jules on 19/11/2016.
 */
@Configuration
public class MatcherApplicationConfig {

    @Bean
    public List<String> connectedNodes() {
        //hardwired for the time being.
        return Arrays.asList("http://localhost:8080", "http://localhost:8081");
    }
}
