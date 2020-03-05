package mate.academy.review.analysis.service.impl;

import java.util.List;

import mate.academy.review.analysis.entity.Review;
import mate.academy.review.analysis.repository.ReviewRepository;
import mate.academy.review.analysis.service.ReviewService;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public void saveAll(List<Review> reviews) {
        reviewRepository.saveAll(reviews);
    }
}
