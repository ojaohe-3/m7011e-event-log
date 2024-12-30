package m7011e.thehomericodyssey.eventlog.orm;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import lombok.Data;
import m7011e.thehomericodyssey.eventlogmodels.models.EventType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "vendor_event",
        indexes = {
                @Index(name = "idx_created_at", columnList = "created_at"),
                @Index(name = "idx_issuer_user_id", columnList = "issuer_user_id")
        })
@Data
public class VendorEventDb {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Version private int version;

  @CreationTimestamp private LocalDateTime createdAt;

  @UpdateTimestamp private LocalDateTime updatedAt;

  private UUID issuerUserId;

  private String issuerUsername;

  private String eventMessage;

  private EventType eventType;

  private UUID targetUserId;

  private UUID targetProductId;

  @OneToMany(cascade = CascadeType.ALL, targetEntity = MetaDataDb.class)
  @JoinColumn(name = "vendor_event_id")
  private Set<MetaDataDb> metaData;
}
