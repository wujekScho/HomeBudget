package pl.piotrschodzinski.homebudget.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
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
    LocalDateTime time;
    @ManyToOne
    ExpenseCategory category;
    @ManyToOne
    User user;

    @PrePersist
    public void setExpenseTime() {
        this.time = LocalDateTime.now();
    }
}
