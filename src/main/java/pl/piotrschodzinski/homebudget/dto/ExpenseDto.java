package pl.piotrschodzinski.homebudget.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExpenseDto {
    @PositiveOrZero
    Long id;
    @NotBlank
    String title;
    @DecimalMin(value = "0.01", inclusive = true)
    BigDecimal value;
    @PastOrPresent
    LocalDateTime time;
    @PositiveOrZero
    Long categoryId;
    @PositiveOrZero
    Long userId;
}
