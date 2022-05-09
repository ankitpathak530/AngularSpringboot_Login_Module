package com.cognizant.jwt;

import com.cognizant.jwt.mdoel.UserEntity;
import com.cognizant.jwt.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Random;

@SpringBootApplication
@AllArgsConstructor
public class JwtApplication implements CommandLineRunner {
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(JwtApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Random random=new Random();
        UserEntity user=new UserEntity();
        Long id = new Long(random.nextInt(100));
        user.setId(id);
        user.setEmail("user"+id+"@gmail.com");
        user.setUsername("user"+id);
        user.setPassword("user"+id);
        user.setEnabled(true);
        user.setRole("Admin");
        UserEntity savedUser = userRepository.save(user);
        System.out.println(savedUser);
    }
}
