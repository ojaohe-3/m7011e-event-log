package m7011e.thehomericodyssey.eventlogmodels.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.UUID;
import m7011e.the_homeric_odyssey.resource_server.exceptions.ErrorMessage;
import m7011e.thehomericodyssey.eventlogmodels.models.VendorEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Events")
public interface VendorEventApi {

  @PostMapping
  @Operation(
      summary = "Create a new vendor event",
      description = "Creates a new vendor event and returns the created event with generated ID")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "201",
            description = "Event created successfully",
            content = @Content(schema = @Schema(implementation = VendorEvent.class))),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid input data",
            content = @Content(schema = @Schema(implementation = ErrorMessage.class)))
      })
  ResponseEntity<VendorEvent> createEvent(
      @Valid @RequestBody @Parameter(description = "Vendor event to create", required = true)
          VendorEvent event);

  @PutMapping("/{id}")
  @Operation(
      summary = "Update an existing vendor event",
      description = "Updates an existing vendor event using optimistic locking with version number")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Event updated successfully",
            content = @Content(schema = @Schema(implementation = VendorEvent.class))),
        @ApiResponse(
            responseCode = "404",
            description = "Event not found",
            content = @Content(schema = @Schema(implementation = ErrorMessage.class))),
        @ApiResponse(
            responseCode = "409",
            description = "Version conflict",
            content = @Content(schema = @Schema(implementation = ErrorMessage.class)))
      })
  ResponseEntity<VendorEvent> updateEvent(
      @PathVariable @Parameter(description = "Event ID", required = true) UUID id,
      @RequestHeader("If-Match")
          @Parameter(description = "Event version for optimistic locking", required = true)
          int version,
      @Valid @RequestBody @Parameter(description = "Updated event data", required = true)
          VendorEvent event);

  @DeleteMapping("/{id}")
  @Operation(
      summary = "Delete a vendor event",
      description = "Deletes an existing vendor event by ID")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "204", description = "Event deleted successfully"),
        @ApiResponse(
            responseCode = "404",
            description = "Event not found",
            content = @Content(schema = @Schema(implementation = ErrorMessage.class)))
      })
  ResponseEntity<Void> deleteEvent(
      @PathVariable @Parameter(description = "Event ID to delete", required = true) UUID id);
}
