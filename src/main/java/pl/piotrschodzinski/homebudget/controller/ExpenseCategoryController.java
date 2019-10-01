package pl.piotrschodzinski.homebudget.controller;

import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.piotrschodzinski.homebudget.dto.ExpenseCategoryDto;
import pl.piotrschodzinski.homebudget.service.ExpenseCategoryService;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@RequestMapping("/categories")
@Validated
public class ExpenseCategoryController {
    private ExpenseCategoryService expenseCategoryService;

    public ExpenseCategoryController(ExpenseCategoryService expenseCategoryService) {
        this.expenseCategoryService = expenseCategoryService;
    }

    @GetMapping("/user/{userId}")
    public List<ExpenseCategoryDto> getUserExpenseCategories(@PathVariable @PositiveOrZero Long userId) {
        return expenseCategoryService.getUserExpenseCategories(userId);
    }

    @PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void editExpenseCategory(@Valid @RequestBody ExpenseCategoryDto expenseCategoryDto) {
        expenseCategoryService.editExpenseCategory(expenseCategoryDto);
    }

    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addExpenseCategory(@Valid @RequestBody ExpenseCategoryDto expenseCategoryDto) {
        expenseCategoryService.addExpenseCategory(expenseCategoryDto);
    }
}
