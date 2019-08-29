package pl.piotrschodzinski.homebudget.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.piotrschodzinski.homebudget.entity.User;
import pl.piotrschodzinski.homebudget.entity.UserRole;
import pl.piotrschodzinski.homebudget.repository.UserRepository;
import pl.piotrschodzinski.homebudget.repository.UserRoleRepository;

@Service
public class UserService {
    private PasswordEncoder passwordEncoder;
    private UserRoleRepository userRoleRepository;
    private UserRepository userRepository;

    public UserService(PasswordEncoder passwordEncoder, UserRoleRepository userRoleRepository, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
    }

    public User addRegularUser(User user) {
        UserRole roleUser = userRoleRepository.findByRole("ROLE_USER");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(roleUser);
        userRepository.save(user);
        return user;
    }
}
