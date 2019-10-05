package pl.piotrschodzinski.homebudget.service;

import org.springframework.stereotype.Service;
import pl.piotrschodzinski.homebudget.dto.ExpenseCategoryDto;
import pl.piotrschodzinski.homebudget.entity.ExpenseCategory;
import pl.piotrschodzinski.homebudget.entity.User;
import pl.piotrschodzinski.homebudget.exception.customException.EntityNotFoundException;
import pl.piotrschodzinski.homebudget.exception.customException.IncorrectIdException;
import pl.piotrschodzinski.homebudget.exception.customException.NotUniqueEntityException;
import pl.piotrschodzinski.homebudget.mapper.ExpenseCategoryMapper;
import pl.piotrschodzinski.homebudget.repository.ExpenseCategoryRepository;
import pl.piotrschodzinski.homebudget.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExpenseCategoryService {
    private ExpenseCategoryRepository expenseCategoryRepository;
    private ExpenseCategoryMapper expenseCategoryMapper;
    private UserRepository userRepository;

    public ExpenseCategoryService(ExpenseCategoryRepository expenseCategoryRepository,
                                  ExpenseCategoryMapper expenseCategoryMapper, UserRepository userRepository) {
        this.expenseCategoryRepository = expenseCategoryRepository;
        this.expenseCategoryMapper = expenseCategoryMapper;
        this.userRepository = userRepository;
    }

    public void setUserDefaultCategories(User user) {
        expenseCategoryRepository.save(new ExpenseCategory("Jedzenie", user));
        expenseCategoryRepository.save(new ExpenseCategory("Rozrywka", user));
        expenseCategoryRepository.save(new ExpenseCategory("Transport", user));
        expenseCategoryRepository.save(new ExpenseCategory("Rachunki", user));
        expenseCategoryRepository.save(new ExpenseCategory("Usługi", user));
        expenseCategoryRepository.save(new ExpenseCategory("Ubrania", user));
        expenseCategoryRepository.save(new ExpenseCategory("Zdrowie", user));
        expenseCategoryRepository.save(new ExpenseCategory("Higiena", user));
    }

    public List<ExpenseCategoryDto> getUserExpenseCategories(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            return expenseCategoryRepository.findAllByUserId(userId).stream()
                    .map(expenseCategoryMapper::mapToDto)
                    .collect(Collectors.toList());
        } else {
            throw new EntityNotFoundException("User not found.");
        }
    }

    public void addExpenseCategory(ExpenseCategoryDto expenseCategoryDto) {
        ExpenseCategory expenseCategory = expenseCategoryMapper.mapToEntity(expenseCategoryDto);
        if (expenseCategory.getId() != null) {
            throw new IncorrectIdException("Id of entity to persist should be null.");
        }
        Optional<ExpenseCategory> optionalExpenseCategory = expenseCategoryRepository.findByName(expenseCategory.getName());
        if (optionalExpenseCategory.isPresent()) {
            throw new NotUniqueEntityException("Category with same name already exists.");
        }
        expenseCategoryRepository.save(expenseCategory);
    }

    public void editExpenseCategory(ExpenseCategoryDto expenseCategoryDto) {
        ExpenseCategory expenseCategory = expenseCategoryMapper.mapToEntity(expenseCategoryDto);
        if (expenseCategory.getId() == null) {
            throw new IncorrectIdException("Id of entity to update shouldn't be null.");
        }
        Optional<ExpenseCategory> optionalExpenseCategory = expenseCategoryRepository.findById(expenseCategory.getId());
        if (!optionalExpenseCategory.isPresent()) {
            throw new EntityNotFoundException("Expense category entity not found.");
        }
        expenseCategoryRepository.save(expenseCategory);
    }

    public void deleteExpenseCategory(Long categoryId) {
        Optional<ExpenseCategory> optionalExpenseCategory = expenseCategoryRepository.findById(categoryId);
        optionalExpenseCategory.ifPresentOrElse(e -> expenseCategoryRepository.delete(e), () -> {
            throw new EntityNotFoundException("Expense category not found.");
        });
    }
}
