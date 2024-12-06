package fit.iuh.edu.vn.backend.resources;

import fit.iuh.edu.vn.backend.dtos.CandidateDTO;
import fit.iuh.edu.vn.backend.models.Candidate;
import fit.iuh.edu.vn.backend.repositories.CandidateRepository;
import fit.iuh.edu.vn.backend.services.CandidateServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/candidates")
public class CandidateResource {
    private final CandidateRepository candidateRepository;
    private final CandidateServices candidateServices;

    public CandidateResource(CandidateRepository candidateRepository, CandidateServices candidateServices) {
        this.candidateRepository = candidateRepository;
        this.candidateServices = candidateServices;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<CandidateDTO>> findAll(){
        return ResponseEntity.ok(candidateServices.findAllCandidates());
    }
}
