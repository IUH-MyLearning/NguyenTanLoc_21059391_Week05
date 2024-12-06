package fit.iuh.edu.vn.frontend.controllers;

import fit.iuh.edu.vn.backend.dtos.PostJobDTO;
import fit.iuh.edu.vn.backend.models.Account;
import fit.iuh.edu.vn.backend.models.Job;
import fit.iuh.edu.vn.backend.repositories.*;
import fit.iuh.edu.vn.backend.services.AuthServices;
import fit.iuh.edu.vn.backend.services.CandidateServices;
import fit.iuh.edu.vn.backend.services.CompanyServices;
import fit.iuh.edu.vn.backend.services.JobServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CompanyController {

    @Autowired
    private CompanyServices companyServices;
    @Autowired
    private JobServices jobServices;
    @Autowired
    private AuthServices authServices;
    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private CandidateServices candidateServices;


    @GetMapping("/company/dashboard")
    public String dashboard(Model model){
        return "companies/dashboard";
    }


    @GetMapping("/company/view-candidates")
    public String showCandidateList(Model model){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = authServices.findByUsername(username);

        model.addAttribute("candidates", candidateServices.findCandidatesBySkills(account.getId()));
        return "companies/candidates";
    }

    @GetMapping("/company/jobs/post")
    public String postJob(Model model){
        // get the id of the current logged in user
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = authServices.findByUsername(username);

        PostJobDTO postJobDTO = new PostJobDTO();
        model.addAttribute("postJobDTO", postJobDTO);
        model.addAttribute("jobs", jobServices.findJobsByCompanyId(account.getId()));
        model.addAttribute("skills", skillRepository.findAll());
        return "jobs/post";
    }

    @PostMapping("/company/jobs/posting")
    public String postJob(@ModelAttribute PostJobDTO postJobDTO){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        postJobDTO.setCompanyId(authServices.findByUsername(username).getId());
        System.out.println("Post job: " + postJobDTO);
        if (companyServices.postJob(postJobDTO)){
            System.out.println("Post job successfully");
        }else{
            System.out.println("Post job failed");
        }
        return "redirect:/dashboard";
    }
}
