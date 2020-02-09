package micro.auth.user.controller;

import micro.auth.user.data.User;
import micro.auth.user.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    private UserRepository userRepository;

    @Resource
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping
    public User createUser(@RequestBody User userData) {
        userData.setPassword(passwordEncoder.encode(userData.getPassword()));
        return userRepository.save(userData);
    }

    @GetMapping("/by-login/{login}")
    public User getUser(@PathVariable String login) {
        return userRepository.findByLogin(login).orElseThrow(() -> new NoSuchElementException("User not found: " + login));
    }

    @GetMapping("/all")
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }
}
