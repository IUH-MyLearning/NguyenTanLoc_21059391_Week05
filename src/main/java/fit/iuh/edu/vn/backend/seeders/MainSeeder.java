package fit.iuh.edu.vn.backend.seeders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MainSeeder implements CommandLineRunner {

    @Autowired
    private CandidateSeeder candidateSeeder;
    @Autowired
    private CompanySeeder companySeeder;
    @Autowired
    private SkillSeeder skillSeeder;
    @Autowired
    private JobSeeder jobSeeder;

    @Override
    public void run(String... args) throws Exception {
        skillSeeder.run(args);
        candidateSeeder.run(args);
        companySeeder.run(args);
        jobSeeder.run(args);
    }
}