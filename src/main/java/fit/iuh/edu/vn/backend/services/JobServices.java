package fit.iuh.edu.vn.backend.services;

import fit.iuh.edu.vn.backend.models.*;
import fit.iuh.edu.vn.backend.repositories.AccountRepository;
import fit.iuh.edu.vn.backend.repositories.CandidateRepository;
import fit.iuh.edu.vn.backend.repositories.CompanyRepository;
import fit.iuh.edu.vn.backend.repositories.JobRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobServices {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CandidateRepository candidateRepository;

    public List<Job> findJobsByCompanyId(Long id){
        Account account = accountRepository.findById(id).orElse(null);
        Company company = companyRepository.findByAccount_Id(account.getId()).orElse(null);
        return company.getJobs();
    }

    public List<Job> findJobsByCandidateSkills(Long id) {
        Account account = accountRepository.findById(id).orElse(null);
        Candidate candidate = candidateRepository.findByAccount_Id(account.getId()).orElse(null);

        List<CandidateSkill> candidateSkills = candidate.getCandidateSkills();

        String query = "SELECT j FROM Job j JOIN j.jobSkills js WHERE js.skill.id IN (" +
                candidateSkills.stream().map(cs -> cs.getSkill().getId().toString()).collect(Collectors.joining(",")) + ")";
        return entityManager.createQuery(query, Job.class).getResultList();
    }


}
