package mate.academy.review.analysis.service;

import mate.academy.review.analysis.entity.User;

public interface UserService {
    User findByLogin(String login);

    User save(User user);
}
