package fit.iuh.edu.vn.backend.repositories;

import fit.iuh.edu.vn.backend.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUsername(String username);
}