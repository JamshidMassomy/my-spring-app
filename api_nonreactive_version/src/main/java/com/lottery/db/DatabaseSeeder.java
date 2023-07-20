package com.lottery.db;

import com.lottery.entity.User;
import com.lottery.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class DatabaseSeeder implements CommandLineRunner {
    private final UserRepository userRepository;

    public DatabaseSeeder(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        List<User> users = generateRandomUsers(30);
        userRepository.saveAll(users);
    }

    private List<User> generateRandomUsers(int count) {
        List<User> users = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            User user = new User();
            user.setFirstname("User" + i);
            user.setPersonalCode(String.format("%04d", random.nextInt(90000)+ 100000));
            users.add(user);
        }
        return users;
    }
}