package fit.iuh.edu.vn;

import fit.iuh.edu.vn.backend.seeders.AccountSeeder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NguyenTanLoc21059391Week05Application {

    public static void main(String[] args) {
        SpringApplication.run(NguyenTanLoc21059391Week05Application.class, args);
    }

    @Autowired
    private AccountSeeder accountSeeder;
    @Bean
    CommandLineRunner initData() {
        return args -> {
            accountSeeder.run(args);
        };
    };

}
