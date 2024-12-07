package orm;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Data;
import m7011e.thehomericodyssey.eventlogmodels.models.EventType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "vendor_event")
@Data
public class VendorEventDb {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Version
    private int version;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private UUID issuerUserId;

    private String issuerUsername;

    private String eventMessage;

    private EventType eventType;

    private UUID targetUserId;

    private UUID targetProductId;

    @OneToOne(cascade = CascadeType.ALL, targetEntity = MetaDataDb.class)
    private MetaDataDb metaData;
}

