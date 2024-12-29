package m7011e.thehomericodyssey.eventlogmodels.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VendorEvent {
    private UUID id;

    private int version;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private UUID issuerUserId;

    private String issuerUsername;

    private String eventMessage;

    private EventType eventType;

    private UUID targetUserId;

    private UUID targetProductId;

    private MetaData metaData;
}
