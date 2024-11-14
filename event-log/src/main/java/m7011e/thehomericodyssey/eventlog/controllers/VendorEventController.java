package m7011e.thehomericodyssey.eventlog.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import m7011e.thehomericodyssey.eventlog.models.ErrorResponseMessage;
import m7011e.thehomericodyssey.eventlog.models.VendorEvent;
import m7011e.thehomericodyssey.eventlog.services.EventPersistenceService;
import m7011e.thehomericodyssey.eventlog.services.EventService;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/vendor-events")
@Tag(name = "Vendor Events", description = "API endpoints for managing vendor events")
@AllArgsConstructor
public class VendorEventController {

    private final EventService eventService;

    @PostMapping
    @Operation(
            summary = "Create a new vendor event",
            description = "Creates a new vendor event and returns the created event with generated ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Event created successfully",
                    content = @Content(schema = @Schema(implementation = VendorEvent.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data",
                    content = @Content(schema = @Schema(implementation = ErrorResponseMessage.class))
            )
    })
    public ResponseEntity<VendorEvent> createEvent(
            @Valid @RequestBody
            @Parameter(description = "Vendor event to create", required = true)
            VendorEvent event) {
        VendorEvent createdEvent = eventService.createEvent(event);
        return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update an existing vendor event",
            description = "Updates an existing vendor event using optimistic locking with version number"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Event updated successfully",
                    content = @Content(schema = @Schema(implementation = VendorEvent.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Event not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponseMessage.class))
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Version conflict",
                    content = @Content(schema = @Schema(implementation = ErrorResponseMessage.class))
            )
    })
    public ResponseEntity<VendorEvent> updateEvent(
            @PathVariable
            @Parameter(description = "Event ID", required = true)
            UUID id,

            @RequestHeader("If-Match")
            @Parameter(description = "Event version for optimistic locking", required = true)
            int version,

            @Valid @RequestBody
            @Parameter(description = "Updated event data", required = true)
            VendorEvent event) {
        try {
            VendorEvent updatedEvent = eventService.updateEvent(id, version, event);
            return ResponseEntity.ok(updatedEvent);
        } catch (OptimisticEntityLockException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a vendor event",
            description = "Deletes an existing vendor event by ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Event deleted successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Event not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponseMessage.class))
            )
    })
    public ResponseEntity<Void> deleteEvent(
            @PathVariable
            @Parameter(description = "Event ID to delete", required = true)
            UUID id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(OptimisticEntityLockException.class)
    public ResponseEntity<ErrorResponseMessage> handleOptimisticLockException(OptimisticEntityLockException ex) {
        ErrorResponseMessage error = new ErrorResponseMessage(
                HttpStatus.CONFLICT.value(),
                "Version conflict: The resource has been modified by another user",
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }
}