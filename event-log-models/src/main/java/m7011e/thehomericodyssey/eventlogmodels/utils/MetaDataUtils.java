package m7011e.thehomericodyssey.eventlogmodels.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.core.util.ObjectMapperFactory;
import java.util.HashSet;
import java.util.Set;
import lombok.experimental.UtilityClass;
import m7011e.thehomericodyssey.eventlogmodels.models.MetaData;

@UtilityClass
public class MetaDataUtils {

  public Set<MetaData> createMetaDataSetFromValue(String value) {
    Set<MetaData> metaDataSet = new HashSet<>();

    for (int i = 0; i < value.length() / 2048; i++) {
      MetaData metaData =
          MetaData.builder().value(value.substring(i * 2048, (i + 1) * 2048)).build();
      metaDataSet.add(metaData);
    }
    return metaDataSet;
  }

  public String javaObjectToString(Object object) throws JsonProcessingException {
    return ObjectMapperFactory.buildStrictGenericObjectMapper().writeValueAsString(object);
  }
}