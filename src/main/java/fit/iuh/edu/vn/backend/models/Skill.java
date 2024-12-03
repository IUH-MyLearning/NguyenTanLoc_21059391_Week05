package fit.iuh.edu.vn.backend.models;

import fit.iuh.edu.vn.backend.converters.SkillTypeConverter;
import fit.iuh.edu.vn.backend.enums.SkillType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "skill")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skill_id", nullable = false)
    private Long id;

    @Column(name = "skill_description")
    private String skillDescription;

    @Column(name = "skill_name")
    private String skillName;

    @Convert(converter = SkillTypeConverter.class)
    @Column(name = "skill_type", nullable = false)
    private SkillType type;

}