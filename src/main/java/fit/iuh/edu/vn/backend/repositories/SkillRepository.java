package fit.iuh.edu.vn.backend.repositories;

import fit.iuh.edu.vn.backend.models.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkillRepository extends JpaRepository<Skill, Long> {
}