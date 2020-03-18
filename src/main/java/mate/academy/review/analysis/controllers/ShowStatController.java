package mate.academy.review.analysis.controllers;

import java.util.List;
import mate.academy.review.analysis.dto.ProductResponseDto;
import mate.academy.review.analysis.dto.UserResponseDto;
import mate.academy.review.analysis.dto.WordResponseDto;
import mate.academy.review.analysis.service.ReviewService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stat")
public class ShowStatController {
    private ReviewService reviewService;

    public ShowStatController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/users")
    public List<UserResponseDto> getMostPopularUsers(@RequestParam(value = "first",
            defaultValue = "1000") Integer mostPopular) {
        return reviewService.findMostPopularUsers(mostPopular);
    }

    @GetMapping("/products")
    public List<ProductResponseDto> getMostCommentedProducts(@RequestParam(value = "first",
            defaultValue = "1000") Integer mostCommented) {
        return reviewService.findMostCommentedProducts(mostCommented);
    }

    @GetMapping("/words")
    public List<WordResponseDto> getMostPopularWords(@RequestParam(value = "first",
            defaultValue = "1000") Integer mostPopular) {
        return reviewService.getMostPopularWords(mostPopular);
    }
}
