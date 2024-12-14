package m7011e.thehomericodyssey.eventlogmodels;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "m7011e.thehomericodyssey.eventlogmodels")
@EnableFeignClients
public class EventLogModelsAutoConfiguration {
}
