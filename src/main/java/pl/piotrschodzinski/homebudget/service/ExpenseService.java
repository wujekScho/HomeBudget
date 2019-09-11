package pl.piotrschodzinski.homebudget.service;

import org.springframework.stereotype.Service;
import pl.piotrschodzinski.homebudget.repository.ExpenseCategoryRepository;
import pl.piotrschodzinski.homebudget.repository.UserRepository;

@Service
public class ExpenseService {
    private ExpenseCategoryRepository expenseCategoryRepository;
    private UserRepository userRepository;

    public ExpenseService(ExpenseCategoryRepository expenseCategoryRepository, UserRepository userRepository) {
        this.expenseCategoryRepository = expenseCategoryRepository;
        this.userRepository = userRepository;
    }
}
