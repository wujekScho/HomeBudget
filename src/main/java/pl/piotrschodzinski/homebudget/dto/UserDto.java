package pl.piotrschodzinski.homebudget.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

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
    @NotBlank //todo create custom validator for password
            String password;
    @NotBlank
    List<String> roles = new ArrayList<>();
}
