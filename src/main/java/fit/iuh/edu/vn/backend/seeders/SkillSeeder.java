package fit.iuh.edu.vn.backend.seeders;

import fit.iuh.edu.vn.backend.enums.SkillType;
import fit.iuh.edu.vn.backend.models.Skill;
import fit.iuh.edu.vn.backend.repositories.SkillRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SkillSeeder implements CommandLineRunner {

    @Autowired
    private SkillRepository skillRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        if (skillRepository.count() == 0) {
            for (int i = 1; i <= 20; i++) {
                Skill skill = new Skill();
                String[] skillList = {
                        "Java", "Python", "JavaScript", "SQL", "HTML", "CSS",
                        "Data Analysis", "Machine Learning", "Project Management",
                        "Leadership", "Communication", "Problem Solving"
                };
                skill.setSkillName(skillList[faker.random().nextInt(0, skillList.length-1)]);
                skill.setSkillDescription(faker.lorem().sentence());
                skill.setType(SkillType.values()[faker.random().nextInt(0,
                        SkillType.values().length-1)]);
                skillRepository.save(skill);
            }
        }
    }
}
