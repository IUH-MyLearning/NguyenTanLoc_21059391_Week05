package fit.iuh.edu.vn.backend.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig  {
    private static final String COMPANY_ROLE = "COMPANY";
    private static final String CANDIDATE_ROLE = "CANDIDATE";

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .usersByUsernameQuery("SELECT username, password, role FROM account WHERE username = ?")
                .authoritiesByUsernameQuery("SELECT username, role FROM account WHERE username = ?")
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/company/**").hasRole(COMPANY_ROLE)
                        .requestMatchers("/candidates/**").hasRole(CANDIDATE_ROLE)
                        .requestMatchers("/candidates-page/**").hasRole(CANDIDATE_ROLE)
                        .requestMatchers("/**").permitAll()
                        .anyRequest().authenticated()
                ).formLogin( form -> form.loginPage("/login")
                        .defaultSuccessUrl("/login", true)
                        .permitAll()).
                httpBasic(Customizer.withDefaults());
        return http.build();
    }


    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("123"));
    }
}