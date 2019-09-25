package pl.piotrschodzinski.homebudget.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.piotrschodzinski.homebudget.entity.Expense;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    @Query("SELECT e FROM Expense e WHERE e.user.id = :userId")
    List<Expense> findAllByUserId(@Param("userId") Long userId);

    @Query("SELECT e FROM Expense e WHERE e.user.id = :userId and e.category.id = :categoryId")
    List<Expense> findAllByUserIdAndCategoryId(@Param("userId") Long userId, @Param("categoryId") Long categoryId);
}
