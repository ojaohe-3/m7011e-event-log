package m7011e.thehomericodyssey.eventlog.models;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class MetaData {
    private UUID id;

    private int version;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String value;
}
