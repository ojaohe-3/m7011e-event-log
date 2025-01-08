package m7011e.thehomericodyssey.eventlogmodels.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import io.swagger.v3.core.util.ObjectMapperFactory;
import java.util.ArrayList;
import java.util.List;
import lombok.experimental.UtilityClass;
import m7011e.thehomericodyssey.eventlogmodels.models.MetaData;
import org.apache.commons.lang3.StringUtils;

@UtilityClass
public class MetaDataUtils {

  private static final int MAX_CHUNK_SIZE = 2048;

  /**
   * Splits a string into chunks of maximum 2048 characters, preserving word boundaries.
   * If a single word is longer than 2048 characters, it will be split at the 2048 character limit.
   *
   * @param input The input string to be split into chunks
   * @return List of strings, each no longer than 2048 characters
   * @throws IllegalArgumentException if input is null
   */
  public List<MetaData> createMetaDataSetFromValue(String input) {
    if (input == null) {
      throw new IllegalArgumentException("Input string cannot be null");
    }

    List<MetaData> chunks = new ArrayList<>();

    if (input.length() <= MAX_CHUNK_SIZE) {
      chunks.add(stringToMetaData(input));
      return chunks;
    }

    int startIndex = 0;
    while (startIndex < input.length()) {
      int endIndex = Math.min(startIndex + MAX_CHUNK_SIZE, input.length());

      if (endIndex < input.length()) {
        int lastSpace = input.lastIndexOf(' ', endIndex);

        if (lastSpace > startIndex && (endIndex - lastSpace) < MAX_CHUNK_SIZE / 4) {
          endIndex = lastSpace;
        }
      }

      String chunk = StringUtils.substring(input, startIndex, endIndex).trim();

      if (StringUtils.isNotEmpty(chunk)) {
        chunks.add(stringToMetaData(chunk));
      }

      startIndex = endIndex;

      while (startIndex < input.length() && input.charAt(startIndex) == ' ') {
        startIndex++;
      }
    }

    return chunks;
  }

  private static MetaData stringToMetaData(String input) {
    return MetaData.builder().value(input).build();
  }

  /**
   * Convenience method to check if a string needs to be chunked
   *
   * @param input The input string to check
   * @return true if the string exceeds the maximum chunk size
   */
  public boolean needsChunking(String input) {
    return input != null && input.length() > MAX_CHUNK_SIZE;
  }

  public String javaObjectToString(Object object) throws JsonProcessingException {
    ObjectMapper mapper = JsonMapper.builder()
            .findAndAddModules()
            .build();
    return mapper.writeValueAsString(object);
  }
}
