package pl.piotrschodzinski.homebudget.mapper;

import org.springframework.stereotype.Component;
import pl.piotrschodzinski.homebudget.dto.ExpenseDto;
import pl.piotrschodzinski.homebudget.entity.Expense;
import pl.piotrschodzinski.homebudget.repository.ExpenseCategoryRepository;
import pl.piotrschodzinski.homebudget.repository.UserRepository;

@Component
public class ExpenseMapper implements DtoMapper<Expense, ExpenseDto> {
    private ExpenseCategoryRepository expenseCategoryRepository;
    private UserRepository userRepository;

    public ExpenseMapper(ExpenseCategoryRepository expenseCategoryRepository, UserRepository userRepository) {
        this.expenseCategoryRepository = expenseCategoryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Expense mapToEntity(ExpenseDto expenseDto) {
        Expense expense = new Expense();
        expense.setId(expenseDto.getId());
        expense.setTitle(expenseDto.getTitle());
        expense.setValue(expenseDto.getValue());
        expense.setTime(expenseDto.getTime());
        expense.setCategory(expenseCategoryRepository.getOne(expenseDto.getCategoryId()));
        expense.setUser(userRepository.getOne(expenseDto.getUserId()));
        return expense;
    }

    @Override
    public ExpenseDto mapToDto(Expense expense) {
        ExpenseDto expenseDto = new ExpenseDto();
        expenseDto.setId(expense.getId());
        expenseDto.setTitle(expense.getTitle());
        expenseDto.setValue(expense.getValue());
        expenseDto.setTime(expense.getTime());
        expenseDto.setCategoryId(expense.getCategory().getId());
        expenseDto.setUserId(expense.getUser().getId());
        return expenseDto;
    }
}
