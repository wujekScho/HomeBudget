package pl.piotrschodzinski.homebudget.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.piotrschodzinski.homebudget.entity.ExpenseCategory;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseCategoryRepository extends JpaRepository<ExpenseCategory, Long> {
    Optional<ExpenseCategory> findByName(String categoryName);

    @Query("SELECT e FROM ExpenseCategory e WHERE e.user.id = :userId")
    List<ExpenseCategory> findAllByUserId(@Param("userId") Long userId);
}
