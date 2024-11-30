package fit.iuh.edu.vn.backend.converters;

import fit.iuh.edu.vn.backend.enums.SkillLevel;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class SkillLevelConverter implements AttributeConverter<SkillLevel, Integer> {
    @Override
    public Integer convertToDatabaseColumn(SkillLevel attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getValue();
    }

    @Override
    public SkillLevel convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }

        return Stream.of(SkillLevel.values())
                .filter(c -> c.getValue() == dbData)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
