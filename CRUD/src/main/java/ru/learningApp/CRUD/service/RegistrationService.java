package ru.learningApp.CRUD.service;

import ru.learningApp.CRUD.dao.abstr.UserRepository;
import ru.learningApp.CRUD.model.Role;
import ru.learningApp.CRUD.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class RegistrationService {

    private UserRepository userRepository;
    private MailService mailService;

    @Autowired
    public RegistrationService(UserRepository userRepository, MailService mailService) {
        this.userRepository = userRepository;
        this.mailService = mailService;
    }

    @Transactional
    public boolean registerUser(User user) {
        Set<Role> roles = new HashSet<>();
        roles.add(new Role(1L, "ROLE_USER"));
        user.setRoles(roles);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        userRepository.insertUser(user);
        try {
            mailService.sendGreetingEmail(user.getEmail());
        } catch (Exception e) {
            System.out.println("Error - " + e);
        }
        return true;
    }
}
