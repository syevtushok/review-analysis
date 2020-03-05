package mate.academy.review.analysis.repository;

import java.util.List;

import mate.academy.review.analysis.dto.ProductResponseDto;
import mate.academy.review.analysis.dto.UserResponseDto;
import mate.academy.review.analysis.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query(value = "select new mate.academy.review.analysis.dto.UserResponseDto(r.profileName,r"
                   + ".userId, count (r.userId)) from Review as r group by r.profileName, r"
                   + ".userId"
                   + " order by count(r.userId) desc")
    List<UserResponseDto> getPopularUsers(Pageable pageable);

    @Query(value = "select new mate.academy.review.analysis.dto.ProductResponseDto(r.productId, "
                   + "count (r.text)) "
                   + "from Review r group by r.productId order by count (r.productId) desc ")
    List<ProductResponseDto> getMostCommentedProducts(Pageable pageable);

    @Query(value = "select new java.lang.String(r.text) from Review r")
    List<String> getMostPopularWords();
}
