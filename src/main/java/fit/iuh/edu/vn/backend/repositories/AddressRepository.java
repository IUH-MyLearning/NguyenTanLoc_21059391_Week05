package fit.iuh.edu.vn.backend.repositories;

import fit.iuh.edu.vn.backend.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
