package m7011e.thehomericodyssey.eventlog.controllers;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.AllArgsConstructor;
import m7011e.thehomericodyssey.eventlog.services.EventService;
import m7011e.thehomericodyssey.eventlogmodels.api.VendorEventApi;
import m7011e.thehomericodyssey.eventlogmodels.models.VendorEvent;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vendor-events")
@Tag(name = "Vendor Events", description = "API endpoints for managing vendor events")
@AllArgsConstructor
public class VendorEventController implements VendorEventApi {

  private final EventService eventService;

  @Override
  @PreAuthorize("hasAnyRole()")
  public ResponseEntity<VendorEvent> updateEvent(
      @PathVariable @Parameter(description = "Event ID", required = true) UUID id,
      @RequestHeader("If-Match")
          @Parameter(description = "Event version for optimistic locking", required = true)
          int version,
      @Valid @RequestBody @Parameter(description = "Updated event data", required = true)
          VendorEvent event) {
    VendorEvent updatedEvent = eventService.updateEvent(id, version, event);
    return ResponseEntity.ok(updatedEvent);
  }

  @Override
  public ResponseEntity<VendorEvent> createEvent(
      @Valid @RequestBody @Parameter(description = "Vendor event to create", required = true)
          VendorEvent event) {
    VendorEvent createdEvent = eventService.createEvent(event);
    return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<Void> deleteEvent(
      @PathVariable @Parameter(description = "Event ID to delete", required = true) UUID id) {
    eventService.deleteEvent(id);
    return ResponseEntity.noContent().build();
  }
}
