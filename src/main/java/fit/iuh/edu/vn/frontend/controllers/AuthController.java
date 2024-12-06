package fit.iuh.edu.vn.frontend.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String login(Principal principal) {
        if (principal != null) {
            String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toArray()[0].toString();
            System.out.println("Role: " + role);
            if (role.equals("ROLE_CANDIDATE")) {
                System.out.println("TRUE");
                return "redirect:/candidate/dashboard";
            }else{
                return "redirect:/company/dashboard";
            }
        }
        return "index";
    }
}
