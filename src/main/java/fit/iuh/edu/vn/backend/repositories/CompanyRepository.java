package fit.iuh.edu.vn.backend.repositories;

import fit.iuh.edu.vn.backend.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByAccount_Id(Long id);

}