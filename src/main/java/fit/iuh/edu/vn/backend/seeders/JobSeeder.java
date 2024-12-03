package fit.iuh.edu.vn.backend.seeders;

import fit.iuh.edu.vn.backend.enums.SkillLevel;
import fit.iuh.edu.vn.backend.models.*;
import fit.iuh.edu.vn.backend.repositories.CompanyRepository;
import fit.iuh.edu.vn.backend.repositories.JobRepository;
import fit.iuh.edu.vn.backend.repositories.JobSkillRepository;
import fit.iuh.edu.vn.backend.repositories.SkillRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class JobSeeder implements CommandLineRunner {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private JobSkillRepository jobSkillRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        Random random = new Random();
        if (jobRepository.count() == 0) {
            List<Company> companies = companyRepository.findAll();
            List<Skill> skills = skillRepository.findAll();
            for (int i = 1; i <= 20; i++) {
                Job job = new Job();
                job.setJobName(faker.job().position());
                job.setJobDesc(faker.lorem().sentence());

                Company company = companies.get(random.nextInt(companies.size()));
                job.setCompany(company);

                jobRepository.save(job);

                int numberOfSkills = random.nextInt(3) + 1; // Mỗi Job có 1-3 kỹ năng
                for (int j = 0; j < numberOfSkills; j++) {
                    Skill skill = skills.get(random.nextInt(skills.size()));

                    JobSkill jobSkill = new JobSkill();
                    JobSkillId jobSkillId = new JobSkillId();
                    jobSkillId.setJobId(job.getId());
                    jobSkillId.setSkillId(skill.getId());

                    jobSkill.setId(jobSkillId);
                    jobSkill.setJob(job);
                    jobSkill.setSkill(skill);
                    jobSkill.setSkillLevel(SkillLevel.values()[random.nextInt(SkillLevel.values().length)]);
                    jobSkill.setMoreInfos(faker.lorem().sentence());

                    // Lưu JobSkill
                    jobSkillRepository.save(jobSkill);
                }
            }
        }

    }
}
