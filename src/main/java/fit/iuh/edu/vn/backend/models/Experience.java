package fit.iuh.edu.vn.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "experience")
public class Experience {
    @Id
    @Column(name = "exp_id", nullable = false)
    private Long id;

    @Column(name = "from_date", nullable = false)
    private LocalDate fromDate;

    @Column(name = "to_date", nullable = false)
    private LocalDate toDate;

    @ColumnDefault("''")
    @Column(name = "role", nullable = false, length = 100)
    private String role;

    @ColumnDefault("''")
    @Column(name = "company", nullable = false, length = 120)
    private String company;

    @ColumnDefault("''")
    @Column(name = "work_desc", nullable = false, length = 400)
    private String workDesc;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @ColumnDefault("0")
    @JoinColumn(name = "can_id", nullable = false)
    private Candidate can;

}