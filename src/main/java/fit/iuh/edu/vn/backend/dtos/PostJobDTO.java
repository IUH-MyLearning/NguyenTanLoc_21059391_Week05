package fit.iuh.edu.vn.backend.dtos;

import fit.iuh.edu.vn.backend.models.Skill;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PostJobDTO {
    private String jobName;
    private String jobDescription;
    private List<String> skills;
    private Long companyId;
}
