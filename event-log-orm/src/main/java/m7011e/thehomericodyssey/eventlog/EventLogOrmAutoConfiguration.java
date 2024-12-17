package m7011e.thehomericodyssey.eventlog;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfiguration
@EntityScan(basePackages = "m7011e.thehomericodyssey.eventlog.orm")
@ComponentScan(basePackages = "m7011e.thehomericodyssey.eventlog")
public class EventLogOrmAutoConfiguration {}
