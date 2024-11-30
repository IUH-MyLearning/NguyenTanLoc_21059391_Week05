package fit.iuh.edu.vn.backend.repositories;

import fit.iuh.edu.vn.backend.models.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}