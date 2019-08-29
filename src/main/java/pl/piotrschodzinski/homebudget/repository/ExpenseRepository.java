package pl.piotrschodzinski.homebudget.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.piotrschodzinski.homebudget.entity.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}