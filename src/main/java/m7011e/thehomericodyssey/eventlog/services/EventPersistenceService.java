package m7011e.thehomericodyssey.eventlog.services;

import lombok.AllArgsConstructor;
import m7011e.thehomericodyssey.eventlog.repositories.VendorEventRepository;
import m7011e.thehomericodyssey.eventlogmodels.models.VendorEvent;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import m7011e.thehomericodyssey.eventlog.orm.VendorEventDb;

import java.util.UUID;

@Service
@AllArgsConstructor
public class EventPersistenceService {

  private final VendorEventRepository vendorEventRepository;

  private final ModelMapper modelMapper;

  public VendorEvent createEntity(VendorEvent event) {
    return mapDbToEntity(vendorEventRepository.saveAndFlush(mapEntityToDb(event)));
  }

  public VendorEvent updateEntity(VendorEvent event) {
    return mapDbToEntity(vendorEventRepository.saveAndFlush(mapEntityToDb(event)));
  }

  public VendorEvent findEntity(UUID id) {
    return mapDbToEntity(vendorEventRepository.findById(id).orElseThrow());
  }

  public void deleteEntity(UUID id) {
    VendorEvent event = findEntity(id);
    vendorEventRepository.delete(mapEntityToDb(event));
  }

  private VendorEventDb mapEntityToDb(final VendorEvent entity) {
    return modelMapper.map(entity, VendorEventDb.class);
  }

  private VendorEvent mapDbToEntity(final VendorEventDb dbEntity) {
    return modelMapper.map(dbEntity, VendorEvent.class);
  }
}
