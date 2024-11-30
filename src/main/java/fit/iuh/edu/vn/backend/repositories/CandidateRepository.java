package fit.iuh.edu.vn.backend.repositories;

import fit.iuh.edu.vn.backend.models.Candidate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface CandidateRepository extends CrudRepository<Candidate, Long> {
    Object findAll(Pageable pageable);
}
