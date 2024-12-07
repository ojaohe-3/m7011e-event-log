package m7011e.thehomericodyssey.eventlog.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class EventConfigurations {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}