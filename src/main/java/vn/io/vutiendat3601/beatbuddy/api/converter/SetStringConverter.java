package vn.io.vutiendat3601.beatbuddy.api.converter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.AttributeConverter;

public class SetStringConverter implements AttributeConverter<Set<String>, String> {

  @Override
  public String convertToDatabaseColumn(Set<String> attribute) {
    return attribute == null ? "" : String.join(",", attribute);
  }

  @Override
  public Set<String> convertToEntityAttribute(String dbData) {
    final Set<String> result = new HashSet<>();
    if (dbData != null && !dbData.isBlank()) {
      result.addAll(List.of(dbData.split(",")));
    }
    return result;
  }
}
