package fit.iuh.edu.vn.backend.repositories;

import fit.iuh.edu.vn.backend.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}