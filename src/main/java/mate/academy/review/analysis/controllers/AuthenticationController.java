package mate.academy.review.analysis.controllers;

import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import mate.academy.review.analysis.configs.JwtTokenUtil;
import mate.academy.review.analysis.dto.UserRequestDto;
import mate.academy.review.analysis.dto.UserResponseDto;
import mate.academy.review.analysis.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsService userDetailsService;
    private AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService,
                                    AuthenticationManager authenticationManager,
                                    JwtTokenUtil jwtTokenUtil,
                                    UserDetailsService userDetailsService) {
        this.authenticationService = authenticationService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(
            @RequestBody UserRequestDto authenticationRequest) {
        authenticate(authenticationRequest.getLogin(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getLogin());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new UserResponseDto(token).getToken());
    }

    @PostMapping("/register")
    public String register(@RequestBody @Valid UserRequestDto userRequestDto) {
        authenticationService.register(userRequestDto.getLogin(),
                userRequestDto.getPassword());
        return "Congratulations! Registration was successful";
    }

    private void authenticate(String username, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));
    }
}
