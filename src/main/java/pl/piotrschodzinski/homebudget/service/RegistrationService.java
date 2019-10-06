package pl.piotrschodzinski.homebudget.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.piotrschodzinski.homebudget.dto.UserDto;
import pl.piotrschodzinski.homebudget.entity.User;
import pl.piotrschodzinski.homebudget.entity.UserRole;
import pl.piotrschodzinski.homebudget.exception.customException.IncorrectIdException;
import pl.piotrschodzinski.homebudget.exception.customException.NotUniqueEntityException;
import pl.piotrschodzinski.homebudget.mapper.UserMapper;
import pl.piotrschodzinski.homebudget.repository.UserRepository;
import pl.piotrschodzinski.homebudget.repository.UserRoleRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class RegistrationService {
    private PasswordEncoder passwordEncoder;
    private UserRoleRepository userRoleRepository;
    private UserRepository userRepository;
    private ExpenseCategoryService expenseCategoryService;
    private UserMapper userMapper;

    public RegistrationService(PasswordEncoder passwordEncoder, UserRoleRepository userRoleRepository,
                               UserRepository userRepository, ExpenseCategoryService expenseCategoryService, UserMapper userMapper) {
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
        this.expenseCategoryService = expenseCategoryService;
        this.userMapper = userMapper;
    }

    @Transactional
    public void addRegularUser(UserDto userDto) {
        User userEntity = userMapper.mapToEntity(userDto);
        if (userEntity.getId() != null) {
            throw new IncorrectIdException("Id of entity to persist should be null.");
        }
        checkUserUniqueness(userEntity);
        UserRole roleUser = userRoleRepository.findByRole("ROLE_USER");
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity.getRoles().add(roleUser);
        userRepository.save(userEntity);
        expenseCategoryService.setUserDefaultCategories(userEntity);
    }

    private void checkUserUniqueness(User userEntity) {
        Optional<User> userByEmail = userRepository.findByEmail(userEntity.getEmail());
        Optional<User> userByUsername = userRepository.findByUsername(userEntity.getUsername());
        if (userByEmail.isPresent()) {
            throw new NotUniqueEntityException("User with this email already exists.");
        }
        if (userByUsername.isPresent()) {
            throw new NotUniqueEntityException("User with this username already exists.");
        }
    }
}
