package pl.piotrschodzinski.homebudget.mapper;

import org.springframework.stereotype.Component;
import pl.piotrschodzinski.homebudget.dto.ExpenseDto;
import pl.piotrschodzinski.homebudget.entity.Expense;
import pl.piotrschodzinski.homebudget.entity.ExpenseCategory;
import pl.piotrschodzinski.homebudget.entity.User;
import pl.piotrschodzinski.homebudget.repository.ExpenseCategoryRepository;
import pl.piotrschodzinski.homebudget.repository.UserRepository;

import java.util.Optional;

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
        Optional<ExpenseCategory> optionalCategory = expenseCategoryRepository.findById(expenseDto.getCategoryId());
        optionalCategory.ifPresentOrElse(expense::setCategory, () -> System.out.println("źle"));
        Optional<User> optionalUser = userRepository.findById(expenseDto.getUserId());
        optionalUser.ifPresentOrElse(expense::setUser, () -> System.out.println("źle")); //todo rzucić tu wyjątkiem??
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
