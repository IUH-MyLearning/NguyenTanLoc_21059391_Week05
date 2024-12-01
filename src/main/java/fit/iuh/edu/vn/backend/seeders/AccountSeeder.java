package fit.iuh.edu.vn.backend.seeders;

import fit.iuh.edu.vn.backend.models.Account;
import fit.iuh.edu.vn.backend.repositories.AccountRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AccountSeeder implements CommandLineRunner {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void run(String... args) throws Exception {
        Account account = new Account();
        Faker faker = new Faker();
//        for (long i = 1; i <= 20; i++) {
//            account.setId(i);
//            account.setEmail(faker.internet().emailAddress());
//            account.setPassword(faker.internet().password());
//            account.setRole(faker.options().option("COMPANY", "CANDIDATE"));
//            accountRepository.save(account);
//        }

    }
}
