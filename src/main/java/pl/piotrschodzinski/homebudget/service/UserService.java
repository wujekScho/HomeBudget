package pl.piotrschodzinski.homebudget.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.piotrschodzinski.homebudget.entity.User;
import pl.piotrschodzinski.homebudget.entity.UserRole;
import pl.piotrschodzinski.homebudget.repository.UserRepository;
import pl.piotrschodzinski.homebudget.repository.UserRoleRepository;

import javax.transaction.Transactional;

@Service
public class UserService {
    private PasswordEncoder passwordEncoder;
    private UserRoleRepository userRoleRepository;
    private UserRepository userRepository;
    private ExpenseCategoryService expenseCategoryService;

    public UserService(PasswordEncoder passwordEncoder, UserRoleRepository userRoleRepository, UserRepository userRepository, ExpenseCategoryService expenseCategoryService) {
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
        this.expenseCategoryService = expenseCategoryService;
    }

    @Transactional
    public User addRegularUser(User user) {
        UserRole roleUser = userRoleRepository.findByRole("ROLE_USER");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(roleUser);
        expenseCategoryService.setDefaultCategories(user);
        userRepository.save(user);
        return user;
    }
}
