package fit.iuh.edu.vn.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comp_id", nullable = false)
    private Long id;

    @Column(name = "about", length = 2000)
    private String about;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "comp_name", nullable = false)
    private String compName;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "web_url")
    private String webUrl;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "address", nullable = false)
    private Address address;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account", nullable = false)
    private Account account;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private List<Job> jobs = new ArrayList<>();

}