package fit.iuh.edu.vn.backend.repositories;

import fit.iuh.edu.vn.backend.models.Candidate;
import fit.iuh.edu.vn.backend.models.Company;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    Optional<Candidate> findByAccount_Id(Long id);
}
