package pl.piotrschodzinski.homebudget.mapper;

import org.springframework.stereotype.Component;
import pl.piotrschodzinski.homebudget.dto.ExpenseCategoryDto;
import pl.piotrschodzinski.homebudget.entity.ExpenseCategory;
import pl.piotrschodzinski.homebudget.entity.User;
import pl.piotrschodzinski.homebudget.exception.customException.EntityNotFoundException;
import pl.piotrschodzinski.homebudget.repository.UserRepository;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ExpenseCategoryMapper implements DtoMapper<ExpenseCategory, ExpenseCategoryDto> {
    private UserRepository userRepository;
    private ExpenseMapper expenseMapper;

    public ExpenseCategoryMapper(UserRepository userRepository, ExpenseMapper expenseMapper) {
        this.userRepository = userRepository;
        this.expenseMapper = expenseMapper;
    }

    @Override
    public ExpenseCategory mapToEntity(ExpenseCategoryDto dtoObject) {
        ExpenseCategory expenseCategory = new ExpenseCategory();
        expenseCategory.setId(dtoObject.getId());
        expenseCategory.setName(dtoObject.getName());
        Optional<User> optionalUser = userRepository.findById(dtoObject.getUserId());
        optionalUser.ifPresentOrElse(expenseCategory::setUser, () -> {
            throw new EntityNotFoundException("User not found.");
        });
        if (dtoObject.getExpenses() != null) {
            expenseCategory.setExpenses(dtoObject.getExpenses().stream()
                    .map(expenseMapper::mapToEntity)
                    .collect(Collectors.toList()));
        }
        return expenseCategory;
    }

    @Override
    public ExpenseCategoryDto mapToDto(ExpenseCategory entityObject) {
        ExpenseCategoryDto expenseCategoryDto = new ExpenseCategoryDto();
        expenseCategoryDto.setId(entityObject.getId());
        expenseCategoryDto.setName(entityObject.getName());
        expenseCategoryDto.setUserId(entityObject.getUser().getId());
        expenseCategoryDto.setExpenses(entityObject.getExpenses().stream()
                .map(expenseMapper::mapToDto)
                .collect(Collectors.toList()));
        return expenseCategoryDto;
    }
}
