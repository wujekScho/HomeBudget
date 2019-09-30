package pl.piotrschodzinski.homebudget.service;

import org.springframework.stereotype.Service;
import pl.piotrschodzinski.homebudget.dto.ExpenseCategoryDto;
import pl.piotrschodzinski.homebudget.entity.ExpenseCategory;
import pl.piotrschodzinski.homebudget.entity.User;
import pl.piotrschodzinski.homebudget.exception.customException.IncorrectIdException;
import pl.piotrschodzinski.homebudget.exception.customException.NotUniqueEntityException;
import pl.piotrschodzinski.homebudget.mapper.ExpenseCategoryMapper;
import pl.piotrschodzinski.homebudget.repository.ExpenseCategoryRepository;

import java.util.Optional;

@Service
public class ExpenseCategoryService {
    private ExpenseCategoryRepository expenseCategoryRepository;
    private ExpenseCategoryMapper expenseCategoryMapper;

    public ExpenseCategoryService(ExpenseCategoryRepository expenseCategoryRepository, ExpenseCategoryMapper expenseCategoryMapper) {
        this.expenseCategoryRepository = expenseCategoryRepository;
        this.expenseCategoryMapper = expenseCategoryMapper;
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

    public void addExpenseCategory(ExpenseCategoryDto expenseCategoryDto) {
        ExpenseCategory expenseCategory = expenseCategoryMapper.mapToEntity(expenseCategoryDto);
        Optional<ExpenseCategory> optionalExpenseCategory = expenseCategoryRepository.findByName(expenseCategory.getName());
        if (optionalExpenseCategory.isPresent()) {
            throw new NotUniqueEntityException("Category with same name already exists.");
        }
        if (expenseCategory.getId() != null) {
            throw new IncorrectIdException("Id of entity to persist should be null.");
        }
        expenseCategoryRepository.save(expenseCategory);
    }
}
