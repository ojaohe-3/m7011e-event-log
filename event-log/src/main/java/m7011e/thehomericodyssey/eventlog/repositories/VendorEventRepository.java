package m7011e.thehomericodyssey.eventlog.repositories;

import m7011e.thehomericodyssey.eventlog.orm.VendorEventDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VendorEventRepository extends JpaRepository<VendorEventDb, UUID> {
}
