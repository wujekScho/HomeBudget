package pl.piotrschodzinski.homebudget.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Table(name = "expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    String title;
    @Column(nullable = false, scale = 2)
    BigDecimal value;
    LocalDateTime time;
    @ManyToOne(optional = false)
    ExpenseCategory category;
    @ManyToOne(optional = false)
    User user;

    @PrePersist
    public void setExpenseTime() {
        this.time = LocalDateTime.now();
    }
}
