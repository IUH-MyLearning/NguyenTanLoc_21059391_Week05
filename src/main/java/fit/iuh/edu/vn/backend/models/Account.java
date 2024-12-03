package fit.iuh.edu.vn.backend.models;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ColumnDefault("'0'")
    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @ColumnDefault("''")
    @Column(name = "password", nullable = false)
    private String password;

//    @Convert(converter = RoleConverter.class)
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

}