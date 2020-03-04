package mate.academy.review.analysis.controllers;

import java.io.File;
import java.util.List;

import javax.annotation.PostConstruct;

import lombok.extern.slf4j.Slf4j;
import mate.academy.review.analysis.entity.Review;
import mate.academy.review.analysis.service.ReviewService;
import mate.academy.review.analysis.util.CsvParserUtils;
import mate.academy.review.analysis.util.FileUtils;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class InitController {
    private final ReviewService reviewService;

    public InitController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostConstruct
    public void init() {
        File file = FileUtils.getFileFromSystem();
        List<Review> reviews = CsvParserUtils.parseCsv(file);
        log.info("Saving data to database");
        reviewService.saveAll(reviews);
        log.info("Successfully saved");
    }
}
