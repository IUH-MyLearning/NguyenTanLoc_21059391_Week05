package fit.iuh.edu.vn.backend.services;

import fit.iuh.edu.vn.backend.dtos.CandidateDTO;
import fit.iuh.edu.vn.backend.models.*;
import fit.iuh.edu.vn.backend.repositories.AccountRepository;
import fit.iuh.edu.vn.backend.repositories.CandidateRepository;
import fit.iuh.edu.vn.backend.repositories.CompanyRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidateServices {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CompanyRepository companyRepository;

    public List<CandidateDTO> findCandidatesBySkills(Long id) {
        Account account = accountRepository.findById(id).orElse(null);
        Company company = companyRepository.findByAccount_Id(account.getId()).orElse(null);

        List<Job> jobs = company.getJobs();
        System.out.println("jobs: " + jobs);
        String query = "SELECT c FROM Candidate c JOIN c.candidateSkills cs WHERE cs.skill.id IN (" +
                jobs.stream().flatMap(j -> j.getJobSkills().stream()).map(js -> js.getSkill().getId()
                        .toString()).collect(Collectors.joining(",")) + ")";
        return entityManager.createQuery(query, Candidate.class).getResultList().stream()
                .map(this::convertToDTO).collect(Collectors.toList());

    }

    public List<CandidateDTO> findAllCandidates(){
        List<Candidate> candidates = candidateRepository.findAll();
        return candidates.stream().map(this::convertToDTO).toList();
    }

    public Page<Candidate> findAll(int pageNo, int pageSize, String sortBy,
                                   String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return (Page<Candidate>) candidateRepository.findAll(pageable);
    }

    private CandidateDTO convertToDTO(Candidate candidate){
        CandidateDTO candidateDTO = new CandidateDTO();
        candidateDTO.setId(candidate.getId());
        candidateDTO.setDob(candidate.getDob());
        candidateDTO.setEmail(candidate.getEmail());
        candidateDTO.setFullName(candidate.getFullName());
        candidateDTO.setPhone(candidate.getPhone());
        candidateDTO.setAddressId(candidate.getAddress().getId().toString());
        candidateDTO.setAccountId(candidate.getAccount().getId().toString());
        return candidateDTO;
    }
}
