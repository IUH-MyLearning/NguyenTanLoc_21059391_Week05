package fit.iuh.edu.vn.backend.repositories;

import fit.iuh.edu.vn.backend.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}