package fit.iuh.edu.vn.backend.repositories;

import fit.iuh.edu.vn.backend.models.JobSkill;
import fit.iuh.edu.vn.backend.models.JobSkillId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobSkillRepository extends JpaRepository<JobSkill, JobSkillId> {
}