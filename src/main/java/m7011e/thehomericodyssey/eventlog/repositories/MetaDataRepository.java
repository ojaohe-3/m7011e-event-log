package m7011e.thehomericodyssey.eventlog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import m7011e.thehomericodyssey.eventlog.orm.MetaDataDb;

import java.util.UUID;

@Repository
public interface MetaDataRepository extends JpaRepository<MetaDataDb, UUID> {
}
