package m7011e.thehomericodyssey.eventlog.configuration;

import m7011e.thehomericodyssey.eventlog.orm.MetaDataDb;
import m7011e.thehomericodyssey.eventlog.orm.VendorEventDb;
import m7011e.thehomericodyssey.eventlogmodels.models.MetaData;
import m7011e.thehomericodyssey.eventlogmodels.models.VendorEvent;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration {

  public void configureModelMapper(ModelMapper modelMapper) {
    modelMapper.typeMap(MetaData.class, MetaDataDb.class);
    modelMapper.typeMap(MetaDataDb.class, MetaDataDb.class);

    modelMapper.typeMap(VendorEvent.class, VendorEventDb.class);
    modelMapper.typeMap(VendorEventDb.class, VendorEvent.class);
  }

  @Bean
  public ModelMapper modelMapper() {
    ModelMapper modelMapper = new ModelMapper();
    configureModelMapper(modelMapper);
    return modelMapper;
  }
}
