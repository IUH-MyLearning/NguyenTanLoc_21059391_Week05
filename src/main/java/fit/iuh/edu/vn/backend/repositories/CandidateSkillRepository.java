package fit.iuh.edu.vn.backend.repositories;

import fit.iuh.edu.vn.backend.models.CandidateSkill;
import fit.iuh.edu.vn.backend.models.CandidateSkillId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateSkillRepository extends JpaRepository<CandidateSkill, CandidateSkillId> {
}