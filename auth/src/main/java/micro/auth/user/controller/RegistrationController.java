package micro.auth.user.controller;


import micro.auth.user.data.User;
import micro.auth.user.data.UserRole;
import micro.auth.user.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/register")
public class RegistrationController {

    @Resource
    private BCryptPasswordEncoder passwordEncoder;

    @Resource
    private UserRepository userRepository;

    @PostMapping
    public User createUser(@RequestBody User userData) {
        if(userRepository.findByLogin(userData.getLogin()).isPresent()) {
            throw new IllegalArgumentException("User with same login already exists");
        }
        // encrypt password before save
        userData.setPassword(passwordEncoder.encode(userData.getPassword()));
        // setup default role (as far our registration are open for everybody)
        userData.setRole(UserRole.CUSTOMER);
        return userRepository.save(userData);
    }
}
