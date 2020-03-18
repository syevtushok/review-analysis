package mate.academy.review.analysis.controllers;

import java.io.File;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import mate.academy.review.analysis.entity.Review;
import mate.academy.review.analysis.entity.Role;
import mate.academy.review.analysis.entity.User;
import mate.academy.review.analysis.service.ReviewService;
import mate.academy.review.analysis.service.RoleService;
import mate.academy.review.analysis.service.UserService;
import mate.academy.review.analysis.util.CsvParserUtils;
import mate.academy.review.analysis.util.FileUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class InitController {
    private ReviewService reviewService;
    private RoleService roleService;
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    public InitController(ReviewService reviewService, RoleService roleService,
                          UserService userService, PasswordEncoder passwordEncoder) {
        this.reviewService = reviewService;
        this.roleService = roleService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        workWithFile();
        createUsers();
    }

    private void createUsers() {
        Role adminRole = new Role();
        adminRole.setRole("ADMIN");
        Role userRole = new Role();
        userRole.setRole("USER");
        roleService.add(adminRole);
        roleService.add(userRole);

        User user = new User();
        user.setLogin("serhii");
        user.setPassword(passwordEncoder.encode("a"));
        user.addRole(roleService.getRole("ADMIN"));
        user.addRole(roleService.getRole("USER"));
        userService.save(user);
    }

    private void workWithFile() {
        File file = FileUtils.getFileFromSystem();
        List<Review> reviews = CsvParserUtils.parseCsv(file);
        log.info("Saving data to database");
        reviewService.saveAll(reviews);
        log.info("Successfully saved");
    }
}
