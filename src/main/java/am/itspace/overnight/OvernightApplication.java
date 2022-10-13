package am.itspace.overnight;

import am.itspace.overnight.entity.RoleUser;
import am.itspace.overnight.entity.Status;
import am.itspace.overnight.entity.StatusSeller;
import am.itspace.overnight.entity.User;
import am.itspace.overnight.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@SpringBootApplication
public class OvernightApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(OvernightApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void run(String... args) throws Exception {
        Optional<User> byEmail = userRepository.findByEmail("admin@gmail.com");
        if (byEmail.isEmpty()) {
            userRepository.save(User.builder()
                    .name("admin")
                    .surname("admin")
                    .email("admin@gmail.com")
                    .password(passwordEncoder().encode("admin"))
                    .phoneNumber("098444444")
                    .role(RoleUser.ADMIN)
                    .status(StatusSeller.ACTIVE)
                    .build());
        }

    }
}
