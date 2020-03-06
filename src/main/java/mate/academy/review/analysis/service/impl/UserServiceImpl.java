package mate.academy.review.analysis.service.impl;

import mate.academy.review.analysis.entity.User;
import mate.academy.review.analysis.repository.UserRepository;
import mate.academy.review.analysis.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

}
