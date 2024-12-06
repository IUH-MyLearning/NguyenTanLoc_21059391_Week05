package fit.iuh.edu.vn.backend.resources;

import fit.iuh.edu.vn.backend.dtos.CandidateDTO;
import fit.iuh.edu.vn.backend.models.Job;
import fit.iuh.edu.vn.backend.services.JobServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyResource {

    private final JobServices jobServices;

    public CompanyResource(JobServices jobServices) {
        this.jobServices = jobServices;
    }
}
