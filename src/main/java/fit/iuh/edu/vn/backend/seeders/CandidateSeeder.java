package fit.iuh.edu.vn.backend.seeders;

import fit.iuh.edu.vn.backend.enums.Role;
import fit.iuh.edu.vn.backend.enums.SkillLevel;
import fit.iuh.edu.vn.backend.models.*;
import fit.iuh.edu.vn.backend.repositories.*;
import jakarta.persistence.EntityManager;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Component
public class CandidateSeeder implements CommandLineRunner {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private CandidateSkillRepository candidateSkillRepository;
    @Autowired
    private ExperienceRepository experienceRepository;

    @Override
    public void run(String... args) {
        Faker faker = new Faker();
        Random random = new Random();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (candidateRepository.count() == 0){
            List<Skill> skills = skillRepository.findAll();
            for (int i = 1; i <= 20; i++) {
                Candidate candidate = new Candidate();
                Address address = new Address();

                candidate.setFullName(faker.name().fullName());

                address.setCity(faker.address().city());
                address.setStreet(faker.address().streetName());
                address.setZipcode(faker.address().zipCode());
                address.setCountry((short) (random.nextInt(5) + 1));
                address.setNumber(faker.address().buildingNumber());
                candidate.setAddress(address);

                candidate.setDob(LocalDate.now().minusYears(faker.random().nextInt(20, 50)));
                candidate.setEmail(faker.internet().emailAddress());
                candidate.setPhone(faker.phoneNumber().cellPhone());

                Account account = new Account();
                account.setUsername(faker.internet().username());
                account.setPassword(passwordEncoder.encode("123"));
                account.setRole(Role.ROLE_CANDIDATE);
                accountRepository.save(account);
                candidate.setAccount(account);
                candidateRepository.save(candidate);

                int numberOfExperiences = random.nextInt(3) + 1; // Mỗi Candidate có 1-3 Experience
                for (int j = 0; j < numberOfExperiences; j++) {
                    Experience experience = new Experience();
                    experience.setCan(candidate);
                    experience.setCompany(faker.company().name());
                    experience.setFromDate(LocalDate.now().minusMonths(faker.random().nextInt(6, 60)));
                    experience.setToDate(LocalDate.now().minusMonths(faker.random().nextInt(0, 6)));
                    experience.setRole(faker.job().position());
                    experience.setWorkDesc(faker.lorem().sentence());
                    experienceRepository.save(experience);
                    candidate.getExperiences().add(experience);
                }

                int numberOfSkills = random.nextInt(3) + 1; // Mỗi Candidate có 1-3 kỹ năng
                for (int j = 0; j < numberOfSkills; j++) {
                    Skill skill = skills.get(random.nextInt(skills.size()));

                    CandidateSkill candidateSkill = new CandidateSkill();
                    CandidateSkillId candidateSkillId = new CandidateSkillId();
                    candidateSkillId.setCanId(candidate.getId());
                    candidateSkillId.setSkillId(skill.getId());

                    candidateSkill.setId(candidateSkillId);
                    candidateSkill.setCan(candidate);
                    candidateSkill.setSkill(skill);
                    candidateSkill.setSkillLevel(SkillLevel.values()[random.nextInt(SkillLevel.values().length)]);
                    candidateSkill.setMoreInfos(faker.lorem().sentence());

                    candidateSkillRepository.save(candidateSkill);
                }
            }
        }

    }
}
