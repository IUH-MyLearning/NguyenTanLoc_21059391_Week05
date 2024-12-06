package fit.iuh.edu.vn.backend.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class CandidateDTO {
    private Long id;
    private LocalDate dob;
    private String email;
    private String fullName;
    private String phone;
    private String addressId;
    private String accountId;

}
