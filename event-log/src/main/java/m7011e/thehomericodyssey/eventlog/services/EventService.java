package m7011e.thehomericodyssey.eventlog.services;

import lombok.AllArgsConstructor;
import m7011e.thehomericodyssey.eventlog.models.VendorEvent;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class EventService {
    private final EventPersistenceService persistenceService;

    private final ModelMapper modelMapper;

    public VendorEvent createEvent(VendorEvent event) {
        return persistenceService.createEntity(event);
    }

    public VendorEvent updateEvent(UUID id, int version, VendorEvent event) {
        VendorEvent oldEvent = persistenceService.findEntity(id);
        if (oldEvent.getVersion() != version) {
            throw new OptimisticEntityLockException(event, "Version mismatch");
        }
        event.setVersion(version);
        event.setId(id);
        modelMapper.map(event, oldEvent);

        return persistenceService.updateEntity(oldEvent);
    }

    public void deleteEvent(UUID id) {
        persistenceService.deleteEntity(id);
    }
}
