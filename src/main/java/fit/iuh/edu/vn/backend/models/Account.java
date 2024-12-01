package fit.iuh.edu.vn.backend.models;

import fit.iuh.edu.vn.backend.converters.RoleConverter;
import fit.iuh.edu.vn.backend.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "account")
public class Account {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @ColumnDefault("''")
    @Column(name = "email", nullable = false)
    private String email;

    @ColumnDefault("''")
    @Column(name = "password", nullable = false)
    private String password;

    @Convert(converter = RoleConverter.class)
    @Column(name = "role", nullable = false)
    private Role role;

}