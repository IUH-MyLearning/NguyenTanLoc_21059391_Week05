package fit.iuh.edu.vn.backend.services;

import fit.iuh.edu.vn.backend.dtos.PostJobDTO;
import fit.iuh.edu.vn.backend.enums.SkillLevel;
import fit.iuh.edu.vn.backend.models.Job;
import fit.iuh.edu.vn.backend.models.JobSkill;
import fit.iuh.edu.vn.backend.models.JobSkillId;
import fit.iuh.edu.vn.backend.models.Skill;
import fit.iuh.edu.vn.backend.repositories.CompanyRepository;
import fit.iuh.edu.vn.backend.repositories.JobRepository;
import fit.iuh.edu.vn.backend.repositories.JobSkillRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyServices {

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private JobSkillRepository jobSkillRepository;


    public boolean postJob(PostJobDTO jobDTO){
        List<Skill> skills = new ArrayList<>();
        for (String skillId: jobDTO.getSkills()){
            Skill skill = new Skill();
            skill.setId(Long.parseLong(skillId));
            skills.add(skill);
        }
        Job job = new Job();
        job.setJobName(jobDTO.getJobName());
        job.setJobDesc(jobDTO.getJobDescription());
        Job jobSave = jobRepository.save(job);

        for (Skill skill: skills){
            JobSkill jobSkill = new JobSkill();
            JobSkillId jobSkillId = new JobSkillId();
            jobSkillId.setJobId(jobSave.getId());
            jobSkillId.setSkillId(skill.getId());

            jobSkill.setId(jobSkillId);
            jobSkill.setJob(jobSave);
            jobSkill.setSkill(skill);
            jobSkill.setSkillLevel(SkillLevel.values()[0]);
            jobSkill.setMoreInfos("More infos");

            jobSkillRepository.save(jobSkill);
        }

        job.setCompany(companyRepository.findById(jobDTO.getCompanyId()).get());
        return true;
    }

}
