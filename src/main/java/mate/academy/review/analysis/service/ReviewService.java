package mate.academy.review.analysis.service;

import java.util.List;

import mate.academy.review.analysis.entity.Review;

public interface ReviewService {
    void saveAll(List<Review> reviews);
}
