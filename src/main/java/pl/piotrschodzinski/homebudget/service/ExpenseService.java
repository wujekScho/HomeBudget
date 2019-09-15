package pl.piotrschodzinski.homebudget.service;

import org.springframework.stereotype.Service;
import pl.piotrschodzinski.homebudget.dto.ExpenseDto;
import pl.piotrschodzinski.homebudget.entity.Expense;
import pl.piotrschodzinski.homebudget.mapper.ExpenseMapper;
import pl.piotrschodzinski.homebudget.repository.ExpenseCategoryRepository;
import pl.piotrschodzinski.homebudget.repository.ExpenseRepository;
import pl.piotrschodzinski.homebudget.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseService {
    private ExpenseCategoryRepository expenseCategoryRepository;
    private ExpenseRepository expenseRepository;
    private UserRepository userRepository;
    private ExpenseMapper expenseMapper;

    public ExpenseService(ExpenseCategoryRepository expenseCategoryRepository, ExpenseRepository expenseRepository, UserRepository userRepository, ExpenseMapper expenseMapper) {
        this.expenseCategoryRepository = expenseCategoryRepository;
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
        this.expenseMapper = expenseMapper;
    }

    public List<ExpenseDto> getUserExpenses(Long userId) {
        List<Expense> userExpenses = expenseRepository.findAllByUserId(userId);
        List<ExpenseDto> userExpensesDto = userExpenses.stream()
                .map(e -> expenseMapper.mapToDto(e))
                .collect(Collectors.toList());
        return userExpensesDto;
    }

    public void addUserExpense(ExpenseDto expenseDto) {
        Expense expenseEntity = expenseMapper.mapToEntity(expenseDto);
        expenseRepository.save(expenseEntity);
    }
}
