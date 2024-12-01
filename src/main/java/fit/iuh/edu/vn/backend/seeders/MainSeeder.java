package fit.iuh.edu.vn.backend.seeders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MainSeeder implements CommandLineRunner {

    @Autowired
    private AccountSeeder accountSeeder;

    @Override
    public void run(String... args) throws Exception {
//        accountSeeder.run(args);
    }
}
