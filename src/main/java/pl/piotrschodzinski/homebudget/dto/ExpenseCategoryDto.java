package pl.piotrschodzinski.homebudget.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExpenseCategoryDto {
    @PositiveOrZero
    Long id;
    @NotBlank
    String name;
    List<ExpenseDto> expenses;
    @PositiveOrZero
    Long userId;
}
