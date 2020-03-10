package mate.academy.review.analysis.security;

import static org.springframework.security.core.userdetails.User.UserBuilder;
import static org.springframework.security.core.userdetails.User.withUsername;

import mate.academy.review.analysis.entity.Role;
import mate.academy.review.analysis.entity.User;
import mate.academy.review.analysis.service.UserService;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userService.findByLogin(login);
        if (user != null) {
            UserBuilder builder = withUsername(login);
            builder.password(user.getPassword());
            builder.roles(getRoles(user));
            return builder.build();
        } else {
            throw new UsernameNotFoundException("User not found.");
        }
    }

    private String[] getRoles(User user) {
        return user.getRoles().stream()
                .map(Role::getRole)
                .toArray(String[]::new);
    }
}
