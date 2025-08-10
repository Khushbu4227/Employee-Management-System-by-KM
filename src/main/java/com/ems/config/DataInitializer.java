package com.ems.config;

import com.ems.model.Role;
import com.ems.model.User;
import com.ems.repository.RoleRepository;
import com.ems.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

	@Bean
	public CommandLineRunner init(RoleRepository roleRepo, UserRepository userRepo, PasswordEncoder passwordEncoder) {
	    return args -> {
	        if (roleRepo.findByName("ROLE_ADMIN").isEmpty()) {
	            Role r = new Role();
	            r.setName("ROLE_ADMIN");
	            roleRepo.save(r);
	        }
	        if (roleRepo.findByName("ROLE_USER").isEmpty()) {
	            Role r = new Role();
	            r.setName("ROLE_USER");
	            roleRepo.save(r);
	        }

	        String adminEmail = "admin@gmail.com";
	        if (userRepo.findByEmail(adminEmail).isEmpty()) {
	            User admin = new User();
	            admin.setEmail(adminEmail);
	            admin.setFullName("Administrator");
	            admin.setPassword(passwordEncoder.encode("Admin123"));
	            admin.setRole("ROLE_ADMIN"); // matches CustomUserDetailsService expectation
	            userRepo.save(admin);
	            System.out.println("Default admin created: " + adminEmail);
	        }
	    };
	}

}
