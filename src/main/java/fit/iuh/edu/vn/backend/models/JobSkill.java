package fit.iuh.edu.vn.backend.models;

import fit.iuh.edu.vn.backend.converters.SkillLevelConverter;
import fit.iuh.edu.vn.backend.enums.SkillLevel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "job_skill")
public class JobSkill {
    @EmbeddedId
    private JobSkillId id;

    @MapsId("jobId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

    @MapsId("skillId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "skill_id", nullable = false)
    private Skill skill;

    @Column(name = "more_infos", length = 1000)
    private String moreInfos;

    @Convert(converter = SkillLevelConverter.class)
    @Column(name = "skill_level", nullable = false)
    private SkillLevel skillLevel;

}