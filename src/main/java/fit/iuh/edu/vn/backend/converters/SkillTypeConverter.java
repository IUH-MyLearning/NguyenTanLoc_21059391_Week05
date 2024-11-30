package fit.iuh.edu.vn.backend.converters;

import fit.iuh.edu.vn.backend.enums.SkillType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class SkillTypeConverter implements AttributeConverter<SkillType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(SkillType attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getValue();
    }

    @Override
    public SkillType convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }

        return Stream.of(SkillType.values())
                .filter(c -> c.getValue() == dbData)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
