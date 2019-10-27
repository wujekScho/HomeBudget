package pl.piotrschodzinski.homebudget.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import pl.piotrschodzinski.homebudget.validator.PasswordConstraint;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {
    @PositiveOrZero
    Long id;
    @NotBlank
    String username;
    @NotBlank
    @Email
    String email;
    @PasswordConstraint
    String password;
    List<String> roles = new ArrayList<>();
}
