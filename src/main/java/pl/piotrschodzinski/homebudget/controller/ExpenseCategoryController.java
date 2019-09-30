package pl.piotrschodzinski.homebudget.controller;

import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.piotrschodzinski.homebudget.dto.ExpenseCategoryDto;
import pl.piotrschodzinski.homebudget.service.ExpenseCategoryService;

import javax.validation.Valid;

@RestController
@RequestMapping("/categories")
@Validated
public class ExpenseCategoryController {
    private ExpenseCategoryService expenseCategoryService;

    public ExpenseCategoryController(ExpenseCategoryService expenseCategoryService) {
        this.expenseCategoryService = expenseCategoryService;
    }

    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addExpenseCategory(@Valid @RequestBody ExpenseCategoryDto expenseCategoryDto) {
        expenseCategoryService.addExpenseCategory(expenseCategoryDto);
    }

}
