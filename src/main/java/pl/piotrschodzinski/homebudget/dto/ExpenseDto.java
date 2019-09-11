package pl.piotrschodzinski.homebudget.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExpenseDto {
    Long id; //todo dodać walidację tutaj
    String title;
    BigDecimal value;
    LocalDateTime time;
    Long categoryId;
    Long userId;
}
