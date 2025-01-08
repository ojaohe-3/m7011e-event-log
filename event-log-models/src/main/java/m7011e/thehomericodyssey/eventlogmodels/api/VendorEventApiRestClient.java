package m7011e.thehomericodyssey.eventlogmodels.api;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
        name = "event-log",
        url = "${event-log.api.url}",
        path = "vendor-events",
        primary = false
)
public interface VendorEventApiRestClient extends VendorEventApi {

}
