package pl.piotrschodzinski.homebudget.controller;

import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.piotrschodzinski.homebudget.dto.ExpenseDto;
import pl.piotrschodzinski.homebudget.service.ExpenseService;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@RequestMapping("/expenses")
@Validated
public class ExpenseController {
    private ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping("/user/{userId}")
    public List<ExpenseDto> getUserExpenses(@PathVariable @PositiveOrZero Long userId) {
        return expenseService.getUserExpenses(userId);
    }

    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addExpense(@Valid @RequestBody ExpenseDto expenseDto) {
        expenseService.addUserExpense(expenseDto);
    }
}
