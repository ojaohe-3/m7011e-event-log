package m7011e.thehomericodyssey.eventlog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import orm.VendorEventDb;

import java.util.UUID;

@Repository
public interface VendorEventRepository extends JpaRepository<VendorEventDb, UUID> {
}
