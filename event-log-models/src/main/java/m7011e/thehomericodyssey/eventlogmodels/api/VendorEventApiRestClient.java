package m7011e.thehomericodyssey.eventlogmodels.api;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
        name = "registry",
        url = "${event-log.api.url}",
        path = "users",
        primary = false
)
public interface VendorEventApiRestClient {

}
