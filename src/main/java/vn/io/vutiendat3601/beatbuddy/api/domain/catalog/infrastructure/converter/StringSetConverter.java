package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.infrastructure.converter;

import jakarta.persistence.AttributeConverter;
import java.util.LinkedList;
import java.util.List;

public class StringSetConverter implements AttributeConverter<List<String>, String> {

  @Override
  public String convertToDatabaseColumn(List<String> attribute) {
    return attribute == null ? "" : String.join(",", attribute);
  }

  @Override
  public List<String> convertToEntityAttribute(String dbData) {
    return dbData == null || dbData.isBlank() ? new LinkedList<>() : List.of(dbData.split(","));
  }
}
