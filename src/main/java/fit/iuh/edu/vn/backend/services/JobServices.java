package fit.iuh.edu.vn.backend.services;

import fit.iuh.edu.vn.backend.models.Account;
import fit.iuh.edu.vn.backend.models.Company;
import fit.iuh.edu.vn.backend.models.Job;
import fit.iuh.edu.vn.backend.repositories.AccountRepository;
import fit.iuh.edu.vn.backend.repositories.CompanyRepository;
import fit.iuh.edu.vn.backend.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServices {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private AccountRepository accountRepository;

    public List<Job> findJobsByCompanyId(Long id){
        Account account = accountRepository.findById(id).orElse(null);
        Company company = companyRepository.findByAccount_Id(account.getId()).orElse(null);
        return company.getJobs();
    }
}
