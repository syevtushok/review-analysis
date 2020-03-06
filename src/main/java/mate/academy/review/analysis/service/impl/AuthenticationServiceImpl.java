package mate.academy.review.analysis.service.impl;

import mate.academy.review.analysis.entity.User;
import mate.academy.review.analysis.exceptions.AuthenticationException;
import mate.academy.review.analysis.service.AuthenticationService;
import mate.academy.review.analysis.service.RoleService;
import mate.academy.review.analysis.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    public AuthenticationServiceImpl(UserService userService,
                                     PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @Override
    public User login(String login, String password) throws AuthenticationException {
        User user = userService.findByLogin(login);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        throw new AuthenticationException("Cannot Sign In with login " + login);
    }

    @Override
    public User register(String login, String password) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(passwordEncoder.encode(password));
        user.addRole(roleService.getRole("USER"));

        return userService.save(user);
    }
}
