package ru.vrn.medsys;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.vrn.medsys.entities.Analysis;
import ru.vrn.medsys.entities.Role;
import ru.vrn.medsys.entities.User;
import ru.vrn.medsys.repositories.AnalysisRepository;
import ru.vrn.medsys.repositories.RoleRepository;
import ru.vrn.medsys.repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class MedsysApplication {
    public static void main(String[] args) {
        SpringApplication.run(MedsysApplication.class, args);
    }

    @Bean
    CommandLineRunner init(UserRepository userRepository, RoleRepository roleRepository, AnalysisRepository analysisRepository) {
        return args -> {
            //init analyses
            Stream.of("Analysis_1", "Analysis_2", "Analysis_3", "Analysis_4").forEach(a ->{
                Analysis an = new Analysis();
                an.setName(a);
                an.setDescription("Very-very-very-very-very-very long description");
                analysisRepository.save(an);
            });
            //init roles
            Stream.of("ROLE_CLIENT", "ROLE_USER", "ROLE_ADMIN").forEach(roleName ->{
                Role role = new Role();
                role.setName(roleName);
                roleRepository.save(role);
            });
            //init users
            Stream.of("test_1", "test_2", "test_3").forEach(name ->{
                User user = new User();
                user.setName(name);
                user.setEmail(name + "@mail.ru");
                user.setPassword(new BCryptPasswordEncoder().encode(name));
                List<Role> roles = StreamSupport.stream(roleRepository.findAll().spliterator(), false)
                        .collect(Collectors.toList());
                user.setRoles(roles);
                userRepository.save(user);
            });
        };
    }
}
