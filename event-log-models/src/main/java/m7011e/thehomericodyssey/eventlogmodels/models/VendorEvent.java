package m7011e.thehomericodyssey.eventlogmodels.models;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

  private Set<MetaData> metaData;
}
