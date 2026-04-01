package com.example.security.config;

import com.example.security.model.User;
import com.example.security.repository.UsersRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DemoUserAdd {
    @Bean
    public CommandLineRunner createAdminUser(UsersRepo usersRepo ,PasswordEncoder passwordEncoder){
        return args -> {
            if(usersRepo.findByUsername("admin").isEmpty()){
                User admin=new User();
                admin.setUsername("admin");
                admin.setName("soumen");
                admin.setEmail("admin@gmail.com");
                admin.setPassword(passwordEncoder.encode("admin1234"));
                admin.setRole("ROLE_ADMIN");

                usersRepo.save(admin);
                System.out.println("default admin user created!");
            }
        };
    }
}
