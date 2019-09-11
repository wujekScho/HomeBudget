package pl.piotrschodzinski.homebudget.controller;

import org.springframework.web.bind.annotation.RestController;
import pl.piotrschodzinski.homebudget.service.ExpenseService;

@RestController
public class ExpenseController {
    private ExpenseService expenseService;

}
