package com.l2code.contato_service.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class BooleanStringConverter implements AttributeConverter<Boolean, String> {

    @Override
    public String convertToDatabaseColumn(Boolean attribute) {
        return Boolean.TRUE.equals(attribute) ? "S" : "N";
    }

    @Override
    public Boolean convertToEntityAttribute(String dbData) {
        return "S".equalsIgnoreCase(dbData);
    }
}
