package fit.iuh.edu.vn.backend.repositories;

import fit.iuh.edu.vn.backend.models.Candidate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

}
