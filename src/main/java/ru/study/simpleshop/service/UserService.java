package ru.study.simpleshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.study.simpleshop.models.Role;
import ru.study.simpleshop.models.User;
import ru.study.simpleshop.repositories.UserRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailSender mailSender;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return userRepository.findByUsername(username);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean activateUser(String code) {
        User user = userRepository.findByActivationCode(code);

        if (user == null) {
            return false;
        }

        user.setActive(true);
        user.setActivationCode(null);

        userRepository.save(user);

        return true;
    }

    public boolean addNewUser(User user) {
        User userFromDb = findByUsername(user.getUsername());

        if (userFromDb != null) return false;

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (findAll().size() == 0) {
            user.setRoles(Arrays.stream(Role.values()).collect(Collectors.toSet()));
        } else {
            user.setRoles(Collections.singleton(Role.USER));
        }

        user.setActivationCode(UUID.randomUUID().toString());
        user.setActive(false);

        userRepository.save(user);

        cartService.addCart(user);

        String message = String.format(
                "Hello, %s \n" +
                        "Welcome to Simple Shop. Please visit a link: localhost:8080/activate/%s",
                user.getUsername(), user.getActivationCode()
        );

        mailSender.send(user.getEmail(), "Activation code", message);

        return true;
    }
}
