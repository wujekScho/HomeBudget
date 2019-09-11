package pl.piotrschodzinski.homebudget.service;

import org.springframework.stereotype.Service;
import pl.piotrschodzinski.homebudget.entity.ExpenseCategory;
import pl.piotrschodzinski.homebudget.entity.User;
import pl.piotrschodzinski.homebudget.repository.ExpenseCategoryRepository;

@Service
public class ExpenseCategoryService {
    private ExpenseCategoryRepository expenseCategoryRepository;

    public ExpenseCategoryService(ExpenseCategoryRepository expenseCategoryRepository) {
        this.expenseCategoryRepository = expenseCategoryRepository;
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
}
