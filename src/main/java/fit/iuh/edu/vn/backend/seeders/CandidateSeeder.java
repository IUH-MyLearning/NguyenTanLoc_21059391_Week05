package fit.iuh.edu.vn.backend.seeders;

import fit.iuh.edu.vn.backend.enums.Role;
import fit.iuh.edu.vn.backend.models.Account;
import fit.iuh.edu.vn.backend.models.Address;
import fit.iuh.edu.vn.backend.models.Candidate;
import fit.iuh.edu.vn.backend.repositories.AccountRepository;
import fit.iuh.edu.vn.backend.repositories.CandidateRepository;
import jakarta.persistence.EntityManager;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Random;

@Component
public class CandidateSeeder implements CommandLineRunner {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void run(String... args) {
        Faker faker = new Faker();
        Random random = new Random();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (candidateRepository.count() == 0){
            for (int i = 1; i <= 20; i++) {
                Candidate candidate = new Candidate();
                Address address = new Address();

                candidate.setFullName(faker.name().fullName());

                address.setCity(faker.address().city());
                address.setStreet(faker.address().streetName());
                address.setZipcode(faker.address().zipCode());
                address.setCountry((short) (random.nextInt(5) + 1));
                address.setNumber(faker.address().buildingNumber());
                candidate.setAddress(address);

                candidate.setDob(LocalDate.now().minusYears(faker.random().nextInt(20, 50)));
                candidate.setEmail(faker.internet().emailAddress());
                candidate.setPhone(faker.phoneNumber().cellPhone());

                Account account = new Account();
                account.setUsername(faker.internet().username());
                account.setPassword(passwordEncoder.encode("123"));
                account.setRole(Role.CANDIDATE);
                accountRepository.save(account);
                candidate.setAccount(account);

                candidateRepository.save(candidate);
            }
        }

    }
}
