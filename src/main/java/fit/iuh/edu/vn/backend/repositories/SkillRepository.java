package fit.iuh.edu.vn.backend.repositories;

import fit.iuh.edu.vn.backend.models.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Long> {
}