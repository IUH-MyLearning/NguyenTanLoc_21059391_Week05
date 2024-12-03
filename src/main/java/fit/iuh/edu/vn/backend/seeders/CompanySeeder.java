package fit.iuh.edu.vn.backend.seeders;

import fit.iuh.edu.vn.backend.enums.Role;
import fit.iuh.edu.vn.backend.models.Account;
import fit.iuh.edu.vn.backend.models.Address;
import fit.iuh.edu.vn.backend.models.Candidate;
import fit.iuh.edu.vn.backend.models.Company;
import fit.iuh.edu.vn.backend.repositories.AccountRepository;
import fit.iuh.edu.vn.backend.repositories.CandidateRepository;
import fit.iuh.edu.vn.backend.repositories.CompanyRepository;
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
public class CompanySeeder implements CommandLineRunner {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void run(String... args) {
        Faker faker = new Faker();
        Random random = new Random();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (companyRepository.count() == 0){
            for (int i = 1; i <= 20; i++) {
                Company company = new Company();
                Address address = new Address();

                company.setCompName(faker.company().name());

                address.setCity(faker.address().city());
                address.setStreet(faker.address().streetName());
                address.setZipcode(faker.address().zipCode());
                address.setCountry((short) (random.nextInt(5) + 1));
                address.setNumber(faker.address().buildingNumber());
                company.setAddress(address);

                company.setAbout(faker.lorem().sentence());
                company.setEmail(faker.internet().emailAddress());
                company.setPhone(faker.phoneNumber().cellPhone());
                company.setWebUrl(faker.internet().url());

                Account account = new Account();
                account.setUsername(faker.internet().username());
                account.setPassword(passwordEncoder.encode("123"));
                account.setRole(Role.COMPANY);
                accountRepository.save(account);
                company.setAccount(account);

                companyRepository.save(company);

            }
        }

    }
}
