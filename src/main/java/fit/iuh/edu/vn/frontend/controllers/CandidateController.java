package fit.iuh.edu.vn.frontend.controllers;

import fit.iuh.edu.vn.backend.models.*;
import fit.iuh.edu.vn.backend.repositories.CandidateRepository;
import fit.iuh.edu.vn.backend.services.AuthServices;
import fit.iuh.edu.vn.backend.services.CandidateServices;
import fit.iuh.edu.vn.backend.services.JobServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
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

    @GetMapping("/candidate/dashboard")
    public String dashboard(Model model){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = authServices.findByUsername(username);
        Candidate candidate = candidateRepository.findByAccount_Id(account.getId()).orElse(null);
        List<Job> jobs = jobServices.findJobsByCandidateSkills(account.getId());
        model.addAttribute("candidate", candidate);
        model.addAttribute("jobs", jobs);
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
        return "companies/candidates-paging";
    }

}
