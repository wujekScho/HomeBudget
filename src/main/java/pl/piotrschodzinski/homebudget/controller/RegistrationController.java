package pl.piotrschodzinski.homebudget.controller;

import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.piotrschodzinski.homebudget.dto.UserDto;
import pl.piotrschodzinski.homebudget.service.RegistrationService;

import javax.validation.Valid;

@RestController
@RequestMapping("/register")
@Validated
public class RegistrationController {
    private RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping(path = "/regular", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void registerRegularUser(@Valid @RequestBody UserDto userDto) {
        registrationService.addRegularUser(userDto);
    }
}
