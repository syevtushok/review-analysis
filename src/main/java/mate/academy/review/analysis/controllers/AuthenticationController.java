package mate.academy.review.analysis.controllers;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;
import mate.academy.review.analysis.dto.UserRequestDto;
import mate.academy.review.analysis.exceptions.AuthenticationException;
import mate.academy.review.analysis.service.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping
public class AuthenticationController {
    private AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public String login(@RequestBody @Valid UserRequestDto userRequestDto) {
        try {
            authenticationService.login(userRequestDto.getLogin(), userRequestDto.getPassword());
            return "Welcome, " + userRequestDto.getLogin() + "!";
        } catch (AuthenticationException e) {
            log.error("Cannot login user with login " + userRequestDto.getLogin()
                      + " and password " + userRequestDto.getPassword(), e);
            return "Please check your login or password";
        }
    }

    @PostMapping("/register")
    public String register(@RequestBody @Valid UserRequestDto userRequestDto) {
        authenticationService.register(userRequestDto.getLogin(),
                userRequestDto.getPassword());
        return "Congratulations! Registration was successful";
    }
}
