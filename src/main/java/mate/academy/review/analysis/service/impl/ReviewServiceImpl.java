package mate.academy.review.analysis.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import mate.academy.review.analysis.dto.ProductResponseDto;
import mate.academy.review.analysis.dto.UserResponseDto;
import mate.academy.review.analysis.dto.WordResponseDto;
import mate.academy.review.analysis.entity.Review;
import mate.academy.review.analysis.repository.ReviewRepository;
import mate.academy.review.analysis.service.ReviewService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @Override
    public List<UserResponseDto> findMostPopularUsers(Integer mostPopular) {
        Pageable top = PageRequest.of(0, Math.toIntExact(mostPopular));
        return reviewRepository.getPopularUsers(top);
    }

    @Override
    public List<ProductResponseDto> findMostCommentedProducts(Integer mostCommented) {
        Pageable top = PageRequest.of(0, Math.toIntExact(mostCommented));
        return reviewRepository.getMostCommentedProducts(top);
    }

    @Override
    public List<WordResponseDto> getMostPopularWords(Integer mostPopular) {
        List<String> allReviewTexts = reviewRepository.getMostPopularWords();
        List<String> splittedWords = new ArrayList<>();
        for (String allReviewText : allReviewTexts) {
            splittedWords.addAll(Arrays.asList(allReviewText.split("[^a-zA-Z0-9']")));
        }
        Map<String, Long> count = splittedWords.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return count.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue((Comparator.reverseOrder())))
                .limit(mostPopular)
                .map((entry) -> new WordResponseDto(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }
}
