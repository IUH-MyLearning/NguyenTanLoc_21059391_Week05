package fit.iuh.edu.vn.frontend.controllers;

import fit.iuh.edu.vn.backend.models.*;
import fit.iuh.edu.vn.backend.repositories.CandidateRepository;
import fit.iuh.edu.vn.backend.repositories.SkillRepository;
import fit.iuh.edu.vn.backend.services.AuthServices;
import fit.iuh.edu.vn.backend.services.CandidateServices;
import fit.iuh.edu.vn.backend.services.JobServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class CandidateController {
    @Autowired
    private CandidateServices candidateServices;
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private AuthServices authServices;
    @Autowired
    private JobServices jobServices;
    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/candidate/dashboard")
    public String dashboard(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = authServices.findByUsername(username);
        Candidate candidate = candidateRepository.findByAccount_Id(account.getId()).orElse(null);
        List<Job> jobs = jobServices.findJobsByCandidateSkills(account.getId());

        List<CandidateSkill> candidateSkills = candidate.getCandidateSkills();
        Set<Long> candidateSkillIds = candidateSkills.stream()
                .map(cs -> cs.getSkill().getId())
                .collect(Collectors.toSet());

        Map<Job, List<Skill>> missingSkillsMap = new HashMap<>();
        for (Job job : jobs) {
            List<Skill> jobSkills = job.getJobSkills().stream()
                    .map(JobSkill::getSkill)
                    .collect(Collectors.toList());
            List<Skill> missingSkills = jobSkills.stream()
                    .filter(skill -> !candidateSkillIds.contains(skill.getId()))
                    .collect(Collectors.toList());
            missingSkillsMap.put(job, missingSkills);
        }
        System.out.println("missingSkillsMap: " + missingSkillsMap);

        model.addAttribute("candidate", candidate);
        model.addAttribute("jobs", jobs);
        model.addAttribute("missingSkillsMap", missingSkillsMap);
        return "candidates/dashboard";
    }

    @GetMapping("/candidates-page")
    public String showCandidateListPaging(Model model,
                                          @RequestParam("page") Optional<Integer> page,
                                          @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        Page<Candidate> candidatePage= candidateServices.findAll(
                currentPage - 1,pageSize,"id","asc");


        model.addAttribute("candidatePage", candidatePage);

        int totalPages = candidatePage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "candidates/candidates-paging";
    }

    @PostMapping("/company/send-email")
    public String sendEmail(@RequestParam("email") String email, Model model) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("Job Opportunity");
            message.setText("Dear Candidate, we have a job opportunity for you. Please contact us for more details.");

            // Gửi email
            mailSender.send(message);

            model.addAttribute("message", "Email sent successfully to " + email);
        } catch (Exception e) {
            model.addAttribute("message", "Error sending email: " + e.getMessage());
            e.printStackTrace();
        }
        return "redirect:/company/dashboard";
    }

}
