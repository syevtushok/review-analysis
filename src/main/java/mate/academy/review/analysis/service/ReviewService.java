package mate.academy.review.analysis.service;

import java.util.List;
import mate.academy.review.analysis.dto.ProductResponseDto;
import mate.academy.review.analysis.dto.UserResponseDto;
import mate.academy.review.analysis.dto.WordResponseDto;
import mate.academy.review.analysis.entity.Review;

public interface ReviewService {
    void saveAll(List<Review> reviews);

    List<UserResponseDto> findMostPopularUsers(Integer mostPopular);

    List<ProductResponseDto> findMostCommentedProducts(Integer mostCommented);

    List<WordResponseDto> getMostPopularWords(Integer mostPopular);
}
