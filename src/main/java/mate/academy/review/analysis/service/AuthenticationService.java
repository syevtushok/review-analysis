package mate.academy.review.analysis.service;

import mate.academy.review.analysis.entity.User;
import mate.academy.review.analysis.exceptions.AuthenticationException;

public interface AuthenticationService {
    User login(String email, String password) throws AuthenticationException;

    User register(String email, String password);
}
